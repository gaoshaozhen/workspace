package cn.shop.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

//import javax.crypto.SecretKey;

public class TokenManager
{
        public static String createJWTString(String userName,Date expires,String key,String userId,Map<String,Object> claims){
                if (userId == null) {
                    throw new NullPointerException("null username is illegal");
                }
                if (expires == null) {
                    throw new NullPointerException("null expires is illegal");
                }
                if (key == null) {
                    throw new NullPointerException("null key is illegal");
                }
                SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;
                String jwtString = Jwts.builder()
                        .setIssuer("Jersey-Security-Basic")
                        .setSubject(userName)
                        .setAudience("user")
                        .setExpiration(expires)
                        .setClaims(claims)
                        .setIssuedAt(new Date())
                        .setId(userId)
                        .signWith(signatureAlgorithm,key)
                        .compact();
                return jwtString;
            }
            public static boolean isValid(String token, String key) {
                try {
                    Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            public static String getName(String jwsToken, String key) {
                if (isValid(jwsToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
                    String name = String.valueOf(claimsJws.getBody().get("name"));
                    return name;
                }
                return null;
            }
            public static String[] getRoles(String jwsToken, String key) {
                if (isValid(jwsToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
                    return claimsJws.getBody().getAudience().split(",");
                }
                return new String[]{};
            }
            public static int getVersion(String jwsToken, String key) {
                if (isValid(jwsToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
                    return Integer.parseInt(claimsJws.getBody().getId());
                }
                return -1;
            }
            /** 
            * @Title: getCompanyId 
            * @Description: TODO(获取企业ID) 
            * @param @param jwsToken
            * @param @param key
            * @param @return    设定文件 
            * @return String    返回类型 
            * @throws 
            */
            public static String getCompanyId(String jwsToken, String key) {
                if (isValid(jwsToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
                    String companyid = String.valueOf(claimsJws.getBody().get("id"));
                  return companyid;
                }
                return null;
            }
            /** 
            * @Title: setAcccessToken 
            * @Description: TODO(存放鉴权中心token) 
            * @param @param accessToken
            * @param @param key
            * @param @return    设定文件 
            * @return String    返回类型 
            * @throws 
            */
            public static void setAcccessToken(String authToken, String key,String accessToken) {
                if (isValid(authToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
                    claimsJws.getBody().put("acessToken", accessToken);
                }
            }
            /** 
            * @Title: getCompanyId 
            * @Description: TODO(获取鉴权中心Token) 
            * @param @param jwsToken
            * @param @param key
            * @param @return    设定文件 
            * @return String    返回类型 
            * @throws 
            */
            public static String getAccessToken(String jwsToken, String key) {
                if (isValid(jwsToken, key)) {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
                    return claimsJws.getBody().getSubject();
                }
                return null;
            }     
}
