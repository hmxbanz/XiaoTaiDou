package com.xtdar.app.server.response;


/**
 * Created by AMing on 15/12/24.
 * Company RongCloud
 */
public class LoginResponse {

    /**
     * code : 200
     * result : {"id":"t1hWCOGvX","token":"B0DA/kKanJviD5xxUzhwsEFIJad0/86YwGxBwz1417WFQi/Vr2OJay26s5IFDffGZaUYRMAkvN0ikvOcTl7RN9JilKZlosfQ"}
     */
    /**
     * id : t1hWCOGvX
     * token : B0DA/kKanJviD5xxUzhwsEFIJad0/86YwGxBwz1417WFQi/Vr2OJay26s5IFDffGZaUYRMAkvN0ikvOcTl7RN9JilKZlosfQ
     */
    private int state;
    private String msg;
    private ResultEntity result;

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public ResultEntity getUserInfo() {
        return result;
    }
    public void setUserInfo(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private String UserID;
        private String UserInfoID;
        private String RongCloudToken;
        private String NickName;
        private String CheckName;
        private String IconSmall;

        public String getCheckName() {
            return CheckName;
        }

        public void setCheckName(String checkName) {
            CheckName = checkName;
        }

        public String getIconSmall() {
            return IconSmall;
        }

        public void setIconSmall(String iconSmall) {
            IconSmall = iconSmall;
        }

        public String getUserID() {
            return UserID;
        }
        public void setUserID(String userID) {
            this.UserID = userID;
        }
        public String getUserInfoID() {
            return UserInfoID;
        }
        public void setUserInfoID(String userInfoID) {
            this.UserInfoID = userInfoID;
        }
        public String getNickName() {
            return NickName;
        }
        public void setNickName(String nickName) {
            this.NickName = nickName;
        }
        public String getRongCloudToken() {
            return RongCloudToken;
        }
        public void setRongCloudToken(String token) {
            this.RongCloudToken = token;
        }
    }
}
