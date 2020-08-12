package com.example.starwars.service;

public class LoadingStatus {

    private boolean loading;

    private LoadingStatus() {
        loading = true;
    }

    public static final class LoadingStatusHolder {
        public static final LoadingStatus HOLDER_INSTANCE = new LoadingStatus();
    }

    public static LoadingStatus getInstance() {
        return LoadingStatusHolder.HOLDER_INSTANCE;
    }


    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
