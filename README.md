# order-stat-batch
주문 상태별 통계 처리 및 리포트 시스템

# 📊 Order Stat Batch

Spring Boot + Spring Batch 기반의 주문 통계 처리 시스템입니다.  
쇼핑몰 주문 데이터를 기반으로 상태별 통계를 계산하고, 결과를 데이터베이스에 저장한 후 콘솔로 리포트 형식으로 출력합니다.

---

## 📌 주요 기능

- 주문(order) 데이터를 매일 자동으로 읽어 통계 생성
- 주문 상태별 건수 및 총 매출 집계 (`ORDERED`, `SHIPPED`, `DELIVERED`, `CANCELLED`, `RETURNED`)
- 통계 결과를 DB(`order_statistics`)에 저장
- 콘솔 로그를 통해 관리자에게 통계 리포트 출력
- Spring Batch의 기본 개념(Reader, Processor, Writer, Step, Job 등)을 학습하기 좋은 구조

---

## 🛠 기술 스택

- Java 17+
- Spring Boot 3.x
- Spring Batch
- MySQL (또는 H2 - 테스트용)
- Lombok
- Gradle (또는 Maven)

---

## 🗂 프로젝트 구조
<h2>📁 프로젝트 디렉토리 구조</h2>

<pre>
com.example.orderstatbatch
├── config/                        # 배치 Job/Step 설정 및 DB 설정
│   ├── BatchJobConfig.java
│   └── DatabaseConfig.java
│
├── job/                           # 배치 작업 단위(Job)별 디렉터리
│   └── orderstat/                # 주문 통계 관련 배치
│       ├── OrderReader.java      # MyBatis를 이용해 주문 데이터 읽기
│       ├── OrderProcessor.java   # 주문 상태별 통계 계산
│       ├── OrderWriter.java      # 통계 DB 저장
│       └── OrderReportTasklet.java # 통계 결과 콘솔 출력
│
├── domain/                        # VO, DTO
│   ├── Order.java
│   ├── Product.java
│   └── OrderStatistic.java
│
├── mapper/                        # MyBatis 매퍼 인터페이스
│   ├── OrderMapper.java
│   └── OrderStatisticMapper.java
│
├── mapper_xml/                    # MyBatis 매퍼 XML 파일 (resources 위치)
│   ├── OrderMapper.xml
│   └── OrderStatisticMapper.xml
│
├── service/                       # 통계 계산 등 비즈니스 로직
│   └── OrderStatService.java
│
└── OrderStatBatchApplication.java # 메인 클래스
</pre>

<h2>📌 디렉토리 설명</h2>

<table border="1" cellpadding="6" cellspacing="0">
  <thead>
    <tr>
      <th>디렉토리</th>
      <th>설명</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>config/</code></td>
      <td>배치 설정 및 데이터베이스(MyBatis 포함) 설정을 담당</td>
    </tr>
    <tr>
      <td><code>job/orderstat/</code></td>
      <td>배치 Job 및 Step 구성 요소 포함 (Reader, Processor, Writer, Tasklet)</td>
    </tr>
    <tr>
      <td><code>domain/</code></td>
      <td>도메인 객체 (Entity, DTO 등)</td>
    </tr>
    <tr>
      <td><code>mapper/</code></td>
      <td>MyBatis 매퍼 인터페이스 (Java)</td>
    </tr>
    <tr>
      <td><code>mapper_xml/</code></td>
      <td>MyBatis XML 매퍼 파일 (쿼리 정의, 일반적으로 <code>resources</code> 내에 위치)</td>
    </tr>
    <tr>
      <td><code>service/</code></td>
      <td>통계 계산 등 비즈니스 로직 처리</td>
    </tr>
    <tr>
      <td><code>OrderStatBatchApplication.java</code></td>
      <td>Spring Boot 메인 실행 클래스</td>
    </tr>
  </tbody>
</table>

<h2>⚙️ MyBatis 설정 예시 (application.yml)</h2>

<pre>
<code>
mybatis:
  type-aliases-package: com.example.orderstatbatch.domain
  mapper-locations: classpath:mapper_xml/*.xml
</code>
</pre>


