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
<h3>📁 프로젝트 디렉터리 구조</h3>

<pre>
com.example.orderstatbatch
├── <b>config/</b>               # 배치 Job/Step 설정 클래스
│   └── <i>BatchJobConfig.java</i>         - 전체 배치 설정 등록
│
├── <b>job/</b>                  # 배치 작업 단위(Job)별 디렉터리
│   └── <b>orderstat/</b>        # 주문 통계 관련 배치
│       ├── <i>OrderReader.java</i>         - 주문 데이터 읽기 (ItemReader)
│       ├── <i>OrderProcessor.java</i>      - 주문 상태별 통계 계산 (ItemProcessor)
│       ├── <i>OrderWriter.java</i>         - 통계 결과 DB 저장 (ItemWriter)
│       └── <i>OrderReportTasklet.java</i>  - 콘솔에 통계 리포트 출력 (Tasklet)
│
├── <b>domain/</b>               # Entity 및 DTO 정의
│   ├── <i>Order.java</i>                - 주문 엔티티
│   ├── <i>Product.java</i>              - 상품 엔티티
│   └── <i>OrderStatistic.java</i>       - 통계 결과 엔티티
│
├── <b>repository/</b>           # Spring Data JPA 리포지토리
│   └── <i>OrderRepository.java</i>
│
├── <b>service/</b>              # 비즈니스 로직 계층
│   └── <i>OrderStatService.java</i>     - 통계 계산 및 헬퍼 서비스
│
└── <b>OrderStatBatchApplication.java</b>  # 메인 클래스 (Spring Boot 실행 진입점)
</pre>

