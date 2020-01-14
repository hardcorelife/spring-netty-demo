package com.example.common.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiweigang
 * @date 2020-01-14 14:27
 */
@Data
@NoArgsConstructor
public class Session {
    // 用户唯一性标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
