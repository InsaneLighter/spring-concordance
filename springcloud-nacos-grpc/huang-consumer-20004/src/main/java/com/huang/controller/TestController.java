package com.huang.controller;

import com.google.common.util.concurrent.ListenableFuture;
import com.test.grpc.api.UserGetRequest;
import com.test.grpc.api.UserGetResponse;
import com.test.grpc.api.UserServiceGrpc;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@Slf4j
public class TestController {

    // 阻塞gRPC
    @GrpcClient("huang-provider-20003")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;
    // 非阻塞gRPC 获得Future对象
    @GrpcClient("huang-provider-20003")
    private UserServiceGrpc.UserServiceFutureStub userServiceFutureStub;
    // 双向流通讯
    @GrpcClient("huang-provider-20003")
    private UserServiceGrpc.UserServiceStub userServiceStub;

    @GetMapping("test1")
    public String test1() {
        // 构建请求数据
        UserGetRequest request = UserGetRequest.newBuilder().setId(1).build();
        // 执行gRPC请求
        UserGetResponse userGetResponse = userServiceBlockingStub.get(request);
        return userGetResponse.toString();
    }

    @SneakyThrows
    @GetMapping("test2")
    public String test2() {
        // 构建请求数据
        UserGetRequest request = UserGetRequest.newBuilder().setId(1).build();
        // 执行gRPC请求
        ListenableFuture<UserGetResponse> future = userServiceFutureStub.get(request);
        return future.get().toString();
    }

    @GetMapping("test3")
    public String test3() {
        // 构建请求数据
        UserGetRequest request = UserGetRequest.newBuilder().setId(1).build();
        // 执行gRPC请求
        Iterator<UserGetResponse> iter = userServiceBlockingStub.getIter(request);
        // 阻塞等待
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
        return "1";
    }

    private StreamObserver<UserGetRequest> requestObserver = null;

    @GetMapping("test4")
    public String test4() {
        // 创建连接
        ClientCallStreamObserver<UserGetResponse> responseObserver = new ClientCallStreamObserver<UserGetResponse>() {
            @Override
            public void onNext(UserGetResponse value) {
                log.info("on next:{}", value.toString());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                requestObserver.onCompleted();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setOnReadyHandler(Runnable onReadyHandler) {

            }

            @Override
            public void disableAutoInboundFlowControl() {

            }

            @Override
            public void request(int count) {

            }

            @Override
            public void setMessageCompression(boolean enable) {

            }

            @Override
            public void cancel(@Nullable String message, @Nullable Throwable cause) {
                log.info("cancel:{}", message);
            }
        };
        this.requestObserver = userServiceStub.callAStream(responseObserver);
        return "1";
    }

    @GetMapping("test5")
    public String test5() {
        // 发送消息
        requestObserver.onNext(UserGetRequest.newBuilder().setId(1).build());
        return "1";
    }

}
