package com.codepirates.securitydesk.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Token {

    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Token(String tokenId) {
        this.tokenId = tokenId;
    }
}
