package com.huang.service;

import com.test.grpc.api.*;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.concurrent.TimeUnit;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void get(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
        responseObserver.onNext(UserGetResponse.newBuilder().setName("aa").setGender(1).build());
        responseObserver.onCompleted();
    }

    @Override
    public void create(UserCreateRequest request, StreamObserver<UserCreateResponse> responseObserver) {
        responseObserver.onNext(UserCreateResponse.newBuilder().setId(1).build());
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void getIter(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
        responseObserver.onNext(UserGetResponse.newBuilder().setId(1).build());
        TimeUnit.SECONDS.sleep(1);
        responseObserver.onNext(UserGetResponse.newBuilder().setId(2).build());
        TimeUnit.SECONDS.sleep(3);
        responseObserver.onNext(UserGetResponse.newBuilder().setId(3).build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UserGetRequest> callAStream(StreamObserver<UserGetResponse> responseObserver) {
        return new StreamObserver<UserGetRequest>() {
            @Override
            public void onNext(UserGetRequest value) {
                responseObserver.onNext(UserGetResponse.newBuilder().setId(value.getId()).build());
                System.out.println(value.toString());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
