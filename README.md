# 🚚 **배송 관리 서비스**
![image](https://github.com/user-attachments/assets/37e0de1e-9741-448f-92e8-7bd526ca4b47)

## **프로젝트 개요**
사용자에게 **배송 요청 생성, 상태 관리, 추적 및 조회**하는 기능을 제공합니다.  


---

## 🛠️ **사용 기술 스택**
- **Backend**: `Java`,`Kotlin` `Spring Boot`, `JPA (Hibernate)`
- **Database**: `MySQL`, 
- **Cache**: `Redis`
- **Deployment**: `Docker`
- **Authentication**: `JWT`
- **Build**: `Gradle`

---
## 👥 **팀원별 역할**  

| 이름         | 역할 및 기여 내용                                      |
|--------------------------|------------------------------------------------------|
| **김성호 (Leader)**      | - slack 메시지 관리<br> - product 관리   |
| **이준석**  | - hub route 최적화 <br>- company 관리               |
| **고혜정** | - order 관리<br>- delivery 관리     |
| **강희진**        | - 유저 관리 <br>- auth 및 gateway 관리 |

---
## 📋 **요구 사항**

### 📌 **요약**

1. **허브 관리**: 허브 정보를 생성하고, 조회 및 수정하며 soft delete로 관리한다.  
2. **업체 관리**: 생산업체와 수령업체를 등록하고, 각 업체는 특정 허브에 소속된다.  
3. **상품 관리**: 업체별 상품 정보를 생성 및 수정하고 허브에 소속시킨다.  
4. **주문 생성**: 수령업체가 상품을 주문하면 재고를 확인하고 주문 정보를 저장한다.  
5. **배송 경로 생성**: 주문 시 허브 간 최적 경로를 설정하고 배송 정보를 생성한다.  
6. **배송 담당자 배정**: 허브 또는 업체 배송 담당자를 순차적으로 배정한다.  
7. **배송 상태 관리**: 배송 상태(대기, 이동 중, 도착)를 실시간으로 업데이트한다.  
8. **Slack 알림**: 발송 허브 담당자에게 최종 발송 시한 및 주문 상태를 슬랙으로 알림. 

---

## 📊 **ERD**

아래는 배송 관리 서비스의 **ERD 설계**입니다:
<img src="https://github.com/user-attachments/assets/697acd81-44e4-4f1b-9938-eff31b06249d" alt="delivery_service_erd" style="border: 1px solid #ddd; border-radius: 5px; ">


---

## 🏗️ **인프라 설계**

서비스의 인프라는 다음과 같이 구성되어 있습니다:  
<img src="https://github.com/user-attachments/assets/482578dd-c752-49be-aa65-0ab3655e2e78" alt="delivery_service_infra" style="border: 1px solid #ddd; border-radius: 5px; width: 500px;">

---

## 🚀 **주요 기능**

### **1. 배송 요청 생성**
요청 정보 입력 시 `요청됨` 상태로 배송 생성  
```
@PostMapping("/deliveries")  
public ResponseEntity<?> createDelivery(@RequestBody DeliveryRequest request) {  
    deliveryService.createDelivery(request);  
    return ResponseEntity.ok().build();  
}
```

---

### **2. 배송 상태 업데이트**
배송 상태를 `요청됨` → `배송 중` → `배송 완료` 순으로 업데이트  
Redis를 통한 동시성 제어 및 Resilience4j로 Fallback 처리  

---

### **3. 배송 이력 조회**
QueryDSL을 사용하여 동적 검색 기능 제공  
조건: 사용자 ID, 배송 상태, 기간  

---

### **4. 데이터 마이그레이션**
Spring Batch를 사용하여 **6개월 이상 지난 데이터를 MongoDB로 이관**  

---

## 🛠️ **트러블 슈팅**

### **1. 동시성 문제**
- **문제**: 동시에 배송 상태를 변경하는 요청 발생.  
- **해결**: Redis의 분산 락과 Fallback 메서드를 활용.

### **2. 데이터 누적 문제**
- **문제**: 오래된 데이터로 인해 조회 성능 저하.  
- **해결**: 
  1. **MariaDB 인덱스 설정**  
  2. **MongoDB로 데이터 이관**  

---

## 🏃‍♂️ **프로젝트 실행 방법**

### **1. Repository Clone**
git clone https://github.com/ksngh/delivery_service.git  
cd delivery_service

---

### **2. Docker로 인프라 실행**
docker run -d --name mariadb-container -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=delivery_db -p 3306:3306 mariadb:latest  
docker run -d --name redis-container -p 6379:6379 redis:latest  
docker run -d --name mongodb-container -p 27017:27017 mongo:6.0  
docker run -d --name prometheus -p 9090:9090 prom/prometheus

---

### **3. Application Build 및 실행**
./gradlew build  
cd build/libs  
java -jar delivery_service-0.0.1-SNAPSHOT.jar

---

### **4. API 문서 확인**
빌드 후 RestDocs를 통해 확인:  

/build/docs/asciidoc/index.html

---

## 🧩 **개선점**
- 메시지 큐(Kafka, RabbitMQ)를 사용해 이벤트 기반 시스템으로 개선.
- ElasticSearch를 활용하여 배송 이력 조회 성능 최적화.
- JPA Custom Repository 패턴 적용으로 코드 유연성 향상.

---

## ⚙️ **작업 방식**
1. **이슈 생성**: 문제나 기능 단위로 이슈를 생성합니다.  
2. **브랜치 생성**: 기능별 브랜치에서 개발을 진행합니다.  
3. **Pull Request**: 코드 리뷰를 통해 병합합니다.
