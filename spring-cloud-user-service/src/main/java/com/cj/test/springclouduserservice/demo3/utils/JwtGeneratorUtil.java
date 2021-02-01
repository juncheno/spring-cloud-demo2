package com.cj.test.springclouduserservice.demo3.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

@Slf4j
public class JwtGeneratorUtil {

    private static String SECRET_KEY="junqquuuuuu";

    public static Key getKeyInstance(){

        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        byte[] keySecret=DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key=new SecretKeySpec(keySecret,signatureAlgorithm.getJcaName());
        return key;

    }
    public static String generatorToken(Map<String, Object> payload){
        String token=null;
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            token=Jwts.builder().setPayload(objectMapper.writeValueAsString(payload)).signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
        } catch (JsonProcessingException e) {
            log.info("generatorToken:"+e);
        }
        return token;

    }

    public  static Claims parseToken(String token){
        Jws<Claims> claimsJws=Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        return claimsJws.getBody();

    }
}
