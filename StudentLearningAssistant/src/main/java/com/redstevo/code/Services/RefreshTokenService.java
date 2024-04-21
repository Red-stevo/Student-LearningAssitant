package com.redstevo.code.Services;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.redstevo.code.Repositories.RefreshTokenRepository;
import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.RefreshTokenTable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    /*
    * This method generate a new uuid that is used as a refresh token
    * It ensures each user has only one refresh token available in the database i.e.
    * deletes other refresh token during generation.
    * The method also sets the expiry of a token{The expiration of the token spans to 14 days.}*/

    public String generateRefreshToken(AuthTable authTable){

        //generate the uuid
        String refreshToken = UUID.randomUUID().toString();

        //delete existing refresh tokens.
        refreshTokenRepository.deleteByAuthTable(authTable);

        //save the new refresh token
        RefreshTokenTable refreshTokenTable = new RefreshTokenTable();

        refreshTokenTable.setRefreshToken(refreshToken);
        refreshTokenTable.setAuthTable(authTable);
        refreshTokenTable.setExpirationDate(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24 * 14));

        //return the refresh token string
        return refreshToken;
    }


   /*
   * This method checks whether the refresh token passed has expired
   * It checks the expiration time set during creation of the token*/

    public Boolean isExpired(String refreshToken){


        return null;
    }
}