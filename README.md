# marketboro 테스트 

- 환경
    
    ```
    
    -spring boot
    -Java 18
    -gradle
    -DataBase
     -mariadb
    ```
    
- DB구성
    - User
        
        
        | userId | 사용자 아이디 |
        | --- | --- |
        | userPointId | 유저 포인트 이력 아이디(마지막) |
        | account | 계정 아이디 |
        | useYn | 사용여부 |
        | regDateTime | 등록일 |
        | modDateTime | 수정일 |
    - UserPoint
        
        
        | userPointId | 유저 포인트 이력 아이디 |
        | --- | --- |
        | useId | 사용자 아이디 |
        | currentPoint | 사용자 최종 포인트 |
        | point | 포인트 |
        | pointStatus | 상태(add, use) |
        | regDateTime | 등록일 |
        | modDateTime | 수정일 |
- 생성 쿼리
    
    ```sql
    drop table user;
    CREATE TABLE `user` (
      `userId` int(10) NOT NULL AUTO_INCREMENT,
      `userPointId` int(10)  NULL  DEFAULT 0 COMMENT '유저 포인트 이력 아이디(마지막)',
      `account` varchar(45) NOT NULL  COMMENT '계정 아이디',
      `useYn` enum('y','n') DEFAULT 'y' COMMENT '사용여부',
      `regDateTime` timestamp NULL DEFAULT NULL COMMENT '등록일시',
      `modDateTime` timestamp NULL DEFAULT NULL COMMENT '수정일시',
      PRIMARY KEY (`userId`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
    
    -- test.userPoint definition
    drop table userPoint;
    CREATE TABLE `userPoint` (
      `userPointId` int(10) NOT NULL AUTO_INCREMENT,
      `userId` int(10) NOT NULL COMMENT '사용자 아이디',
      `currentPoint` int(10) NOT NULL DEFAULT 0 COMMENT '사용자 최종 포인트',
      `point` int(10) NOT NULL DEFAULT 0 COMMENT '포인트',
      `pointStatus` enum('add','use') DEFAULT NULL COMMENT '상태(add, use)',
      `regDateTime` timestamp NULL DEFAULT NULL COMMENT '등록일시',
      `modDateTime` timestamp NULL DEFAULT NULL COMMENT '수정일시',
      PRIMARY KEY (`userPointId`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
    ```
    
- API Test
    - 1. 포인트 적립
        - Method : POST
        - Path : /v1/user/add/point
        - 요청 예시
            
            ```bash
            curl -i -X POST \
               -H "Content-Type:application/x-www-form-urlencoded" \
               -d "userId=1" \
               -d "point=200" \
             'http://localhost:8080/v1/user/add/point'
            ```
            
        - 결과
            
            ```sql
            {
                "httpResult": {
                    "statusCode": 200,
                    "message": "OK"
                },
                "isSuccess": "y",
                "result": {
                    "userPointId": 9,
                    "userId": 1,
                    "point": 200,
                    "currentPoint": 1300,
                    "pointStatus": "ADD",
                    "regDateTime": 1669212094161,
                    "modDateTime": 1669212094161
                }
            }
            ```
            
    - 2. 포인트 사용
        - Method : POST
        - Path : /v1/user/use/point
        - 요청 예시
            
            ```bash
            curl -i -X POST \
               -H "Content-Type:application/x-www-form-urlencoded" \
               -d "userId=1" \
               -d "point=300" \
             'http://localhost:8080/v1/user/use/point'
            ```
            
        - 결과
            
            ```sql
            {
                "httpResult": {
                    "statusCode": 200,
                    "message": "OK"
                },
                "isSuccess": "y",
                "result": {
                    "userPointId": 8,
                    "userId": 1,
                    "point": 300,
                    "currentPoint": 1100,
                    "pointStatus": "USE",
                    "regDateTime": 1669211588624,
                    "modDateTime": 1669211588624
                }
            }
            ```
            
    - 3. 회원별 적립금 적립/사용 내역 조회
        - Method : POST
        - Path :/v1/user/point-history/list
        - 요청 예시
            
            ```bash
            curl -i -X POST \
               -H "Content-Type:application/x-www-form-urlencoded" \
               -d "userId=1" \
               -d "page=0" \
               -d "size=10" \
             'http://localhost:8080/v1/user/point-history/list'
            ```
            
        - 결과
            
            ```sql
            {
                "httpResult": {
                    "statusCode": 200,
                    "message": "OK"
                },
                "isSuccess": "y",
                "result": {
                    "content": [
                        {
                            "userPointId": 9,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 1300,
                            "pointStatus": "ADD",
                            "regDateTime": 1669212094000,
                            "modDateTime": 1669212094000
                        },
                        {
                            "userPointId": 8,
                            "userId": 1,
                            "point": 300,
                            "currentPoint": 1100,
                            "pointStatus": "USE",
                            "regDateTime": 1669211588000,
                            "modDateTime": 1669211588000
                        },
                        {
                            "userPointId": 7,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 1400,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211535000,
                            "modDateTime": 1669211535000
                        },
                        {
                            "userPointId": 6,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 1200,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211402000,
                            "modDateTime": 1669211402000
                        },
                        {
                            "userPointId": 5,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 1000,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211399000,
                            "modDateTime": 1669211399000
                        },
                        {
                            "userPointId": 4,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 800,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211392000,
                            "modDateTime": 1669211392000
                        },
                        {
                            "userPointId": 3,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 600,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211389000,
                            "modDateTime": 1669211389000
                        },
                        {
                            "userPointId": 2,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 400,
                            "pointStatus": "ADD",
                            "regDateTime": 1669211346000,
                            "modDateTime": 1669211346000
                        },
                        {
                            "userPointId": 1,
                            "userId": 1,
                            "point": 200,
                            "currentPoint": 200,
                            "pointStatus": "ADD",
                            "regDateTime": null,
                            "modDateTime": null
                        }
                    ],
                    "pageable": {
                        "sort": {
                            "empty": true,
                            "unsorted": true,
                            "sorted": false
                        },
                        "offset": 0,
                        "pageNumber": 0,
                        "pageSize": 10,
                        "paged": true,
                        "unpaged": false
                    },
                    "last": true,
                    "totalElements": 9,
                    "totalPages": 1,
                    "first": true,
                    "size": 10,
                    "number": 0,
                    "sort": {
                        "empty": true,
                        "unsorted": true,
                        "sorted": false
                    },
                    "numberOfElements": 9,
                    "empty": false
                }
            }
            ```
            
    - 4. 회원별 적립금 합계 조회
        - Method : POST
        - Path :/v1/user/sum/point
        - 요청 예시
            
            ```bash
            curl -i -X POST \
               -H "Content-Type:application/x-www-form-urlencoded" \
               -d "userId=1" \
             'http://localhost:8080/v1/user/sum/point'
            ```
            
        - 결과
            
            ```sql
            {
                "httpResult": {
                    "statusCode": 200,
                    "message": "OK"
                },
                "isSuccess": "y",
                "result": {
                    "userPointId": 9,
                    "userId": 1,
                    "point": 200,
                    "currentPoint": 1300,
                    "pointStatus": "ADD",
                    "regDateTime": 1669212094000,
                    "modDateTime": 1669212094000
                }
            }
            ```
            
---

- 과제 결과 : 떨어짐
- 떨어진 이유 : 테스트 코드를 짜지 않아서 인것 같다.
