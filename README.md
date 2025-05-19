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

- Java 17
- Spring Boot 3.3.11
- Spring Batch
- MySQL
- Lombok
- Gradle

---

## 📁 프로젝트 디렉토리 구조</h2>
<pre>
com.example.orderstatbatch
├── config/                        # 배치 Job/Step 설정 및 DB 설정
│   ├── BatchJobConfig.java        # 배치 Job/Step 설정
│   └── DatabaseConfig.java        # DB 설정 (MyBatis 연결)
│
├── job/                           # 배치 작업 단위(Job)별 디렉터리
│   └── orderstat/                 # 주문 통계 관련 배치
│       ├── ReadOrdersTasklet.java # MyBatis를 이용해 주문 데이터 읽기
│       ├── WriteSettlementTasklet.java # 정산 결과 DB 저장
│       └── OrderReportTasklet.java # 통계 결과 콘솔 출력
│
├── domain/                        # VO, DTO
│   ├── Order.java                 # 주문 도메인 객체
│   ├── Product.java               # 상품 도메인 객체
│   ├── OrderDetails.java          # 주문 상세 도메인 객체
│   └── OrderSettlement.java       # 정산 결과 도메인 객체
│
├── mapper/                        # MyBatis 매퍼 인터페이스
│   ├── OrderMapper.java           # 주문 관련 MyBatis 매퍼 인터페이스
│   └── OrderStatisticMapper.java  # 통계 관련 MyBatis 매퍼 인터페이스
│
├── mapper_xml/                    # MyBatis 매퍼 XML 파일 (resources 위치)
│   ├── OrderMapper.xml            # 주문 관련 MyBatis 매퍼 XML
│   └── OrderStatisticMapper.xml   # 통계 관련 MyBatis 매퍼 XML
│
├── service/                       # 통계 계산 등 비즈니스 로직
│   └── OrderStatService.java      # 주문 통계 계산 서비스
│   └── OrderStatisticService.java # 통계 계산 서비스
│   └── SettlementService.java     # 정산 결과 저장 서비스
│
└── OrderStatBatchApplication.java # Spring Boot 메인 클래스
</pre>


<h2>📌 디렉토리 설명</h2>
<table border="1" cellpadding="6" cellspacing="0">
  <thead>
    <tr>
      <th>디렉토리/파일</th>
      <th>설명</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>config/</code></td>
      <td>
        배치 작업(Job)과 Step 설정 및 데이터베이스 연결 설정을 담당하는 클래스들입니다. <br/><br/>
        <code>BatchJobConfig.java</code>: 배치 작업 및 Step을 설정하는 클래스입니다. <br/><br/>
        <code>DatabaseConfig.java</code>: 데이터베이스 연결 설정 및 MyBatis 설정을 담당합니다.
      </td>
    </tr>
    <tr>
      <td><code>job/orderstat/</code></td>
      <td>
        주문 통계 관련 배치 작업이 정의된 디렉토리입니다. <br/><br/>
        <code>ReadOrdersTasklet.java</code>: MyBatis를 이용해 주문 데이터를 읽어오는 작업을 수행합니다. <br/><br/>
        <code>WriteSettlementTasklet.java</code>: 정산 결과를 DB에 저장하는 작업을 수행합니다. <br/><br/>
        <code>OrderReportTasklet.java</code>: 배치 실행 결과(통계)를 콘솔에 출력합니다.
      </td>
    </tr>
    <tr>
      <td><code>domain/</code></td>
      <td>
        도메인 객체(VO, DTO 등)가 위치한 디렉토리입니다. <br/><br/>
        <code>Order.java</code>: 주문 관련 정보를 담고 있는 도메인 객체입니다. <br/><br/>
        <code>Product.java</code>: 상품 관련 정보를 담고 있는 도메인 객체입니다. <br/><br/>
        <code>OrderDetails.java</code>: 주문 상세 정보를 담고 있는 도메인 객체입니다. <br/><br/>
        <code>OrderSettlement.java</code>: 정산 결과를 담고 있는 도메인 객체입니다.
      </td>
    </tr>
    <tr>
      <td><code>mapper/</code></td>
      <td>
        MyBatis 매퍼 인터페이스가 위치한 디렉토리입니다. <br/><br/>
        <code>OrderMapper.java</code>: 주문과 관련된 MyBatis 쿼리를 정의하는 인터페이스입니다. <br/><br/>
        <code>OrderStatisticMapper.java</code>: 주문 통계 및 정산과 관련된 MyBatis 쿼리를 정의하는 인터페이스입니다.
      </td>
    </tr>
    <tr>
      <td><code>mapper_xml/</code></td>
      <td>
        MyBatis의 XML 매퍼 파일들이 위치하는 디렉토리입니다. <br/><br/>
        <code>OrderMapper.xml</code>: 주문과 관련된 SQL 쿼리를 정의한 XML 파일입니다. <br/><br/>
        <code>OrderStatisticMapper.xml</code>: 주문 통계 및 정산 관련 SQL 쿼리를 정의한 XML 파일입니다.
      </td>
    </tr>
    <tr>
      <td><code>service/</code></td>
      <td>
        비즈니스 로직 및 통계 계산, 정산 결과 저장 등을 담당하는 서비스 클래스들이 위치한 디렉토리입니다. <br/><br/>
        <code>OrderStatService.java</code>: 주문 통계 계산과 관련된 비즈니스 로직을 수행합니다. <br/><br/>
        <code>OrderStatisticService.java</code>: 전체적인 통계 계산을 처리하는 서비스입니다. <br/><br/>
        <code>SettlementService.java</code>: 정산 결과를 처리하고 저장하는 서비스입니다.
      </td>
    </tr>
    <tr>
      <td><code>OrderStatBatchApplication.java</code></td>
      <td>
        Spring Boot 메인 클래스입니다. 애플리케이션의 진입점이며 배치 작업을 실행합니다.
      </td>
    </tr>
  </tbody>
</table>



