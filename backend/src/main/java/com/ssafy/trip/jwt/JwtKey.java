package com.ssafy.trip.jwt;

import io.jsonwebtoken.security.Keys;
import javafx.util.Pair;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JwtKey {
        /**
         * Kid Key List 외부로 절대 유출되어서는 안된다.(테스트용)
         */
        private static final Map<String, String> SECRET_KEY_SET = new HashMap<String, String>() {
            {
                put("key1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
                put("key2", "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ");
                put("key3", "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
            }
        };

        private static final String[] KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
        private static Random randomIndex = new Random();

        /**
         * SECRET_KEY_SET 에서 랜덤한 KEY 가져오기
         *
         * @return kid와 key Pair
         */
        public static Pair<String, Key> getRandomKey() {
            String kid = KID_SET[randomIndex.nextInt(KID_SET.length)];
            String secretKey = SECRET_KEY_SET.get(kid);
            return new Pair<>(kid, Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)));
        }

        /**
         * kid로 Key찾기
         *
         * @param kid kid
         * @return Key
         */
        public static Key getKey(String kid) {
            String key = SECRET_KEY_SET.getOrDefault(kid, null);
            if (key == null) {
                return null;
            }
            return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));


    }
}
