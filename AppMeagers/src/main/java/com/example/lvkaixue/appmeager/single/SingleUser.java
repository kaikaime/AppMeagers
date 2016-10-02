package com.example.lvkaixue.appmeager.single;

/**
 * Created by lvkaixue on 2016/8/5.
 */
public class SingleUser {
    private SingleUser(){}
    private static SingleUser mSngleUser;
    /*private String oauthConsumerKey="100330589";
    private String accessToken ="EB8ECF44D132DC2F22AF9DC2311F636E";
    private String openId = "B20A160AE7E0742A165857381A29176C";*/
    private String oauthConsumerKey;
    private String accessToken;
    private String openId;
    private String authorityCost;
    private String expiresIn;
    private String loginCost;
    private String pf;
    private String pyKey;
    private String queryAuthorityCost;

    public static SingleUser getSingleUser(){
       if(mSngleUser == null){
            synchronized (SingleUser.class){
                if(mSngleUser == null){
                    mSngleUser = new SingleUser();
                }
            }
        }
        return mSngleUser;
    }

    public String getOauthConsumerKey() {
        return oauthConsumerKey;
    }

    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAuthorityCost() {
        return authorityCost;
    }

    public void setAuthorityCost(String authorityCost) {
        this.authorityCost = authorityCost;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getLoginCost() {
        return loginCost;
    }

    public void setLoginCost(String loginCost) {
        this.loginCost = loginCost;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPyKey() {
        return pyKey;
    }

    public void setPyKey(String pyKey) {
        this.pyKey = pyKey;
    }

    public String getQueryAuthorityCost() {
        return queryAuthorityCost;
    }

    public void setQueryAuthorityCost(String queryAuthorityCost) {
        this.queryAuthorityCost = queryAuthorityCost;
    }

}
