REST API Server  ( ~ing ) 🐻
============ 
Java 8 to 11 <br>
Spring Security <br>
HateOAS, Self-descriptive message<br>
TDD - JUnit4<br>

-------
* Author 민경재[ggomjae] <br>
* 개인 개발 블로그 링크 <https://blog.naver.com/ggomjae> <br>
* 과거 공부한 express 설계 참고 <https://blog.naver.com/ggomjae/222049288099> <br>
* 과거 공부한 java 8 to 10 참고 <https://github.com/ggomjae/java8to11> <br>
-------
### REST API
<div>
    <img height="300" src = "https://user-images.githubusercontent.com/43604493/100344279-ec37ca80-3023-11eb-9e3e-486bb961a874.JPG">
</div>

<br>

One ) ```DataFilter```는 ```@jsonignore```,```SimpleBeanPropertyFilter```를 쓰지 않고 ```DTO```로 반환

```bash
EX] 
@jsonignore --- ( x )
SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.fillterOutAllExcept --- ( x )

@PostMapping("/users") --- ( O )
public ResponseEntity<ResponseCreateDto> createUser(@Valid @RequestBody RequestCreateDto requestCreateDto)
    ...
    return ResponseEntity.created(location).body(responseCreateDto);
```

<br>

Two ) ```Entity```가 아닌 ```requestDTO```에 ```@Valid```로 적용
```bash
public ResponseEntity<ResponseCreateDto> createUser(@Valid @RequestBody RequestCreateDto requestCreateDto)
```

### ERD

### SQL

# 끝맺음
<br>

> 완전한 코드는 Git 위의 코드 부분을 봐주세요. <br>
