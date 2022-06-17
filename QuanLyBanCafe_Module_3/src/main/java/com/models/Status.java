package com.models;

public enum Status {

        ACTIVE("ACTIVE"),
        LOCK("LOCK");

        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static Status parseRole(String value) {
            Status[] values = values();
            for (Status status: values) {
                if(status.value.equals(value)){
                    return status;
                }
            }
            throw new IllegalArgumentException("invalid");
        }

    }

