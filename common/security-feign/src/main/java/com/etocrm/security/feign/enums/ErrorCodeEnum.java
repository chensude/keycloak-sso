package com.etocrm.security.feign.enums;

public enum ErrorCodeEnum {
        GL99990100(9999100, "参数异常");
        private int code;
        private String msg;

        public String msg() {
            return msg;
        }
        public int code() {
            return code;
        }

        ErrorCodeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * get Enums
         *
         */
        public static ErrorCodeEnum getEnum(int code) {
            for (ErrorCodeEnum el: ErrorCodeEnum.values()) {
                if(el.code==code) {
                    return el;
                }
            }
            return null;
        }
}
