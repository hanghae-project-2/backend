# 🚚 **배송 관리 서비스**

## **프로젝트 개요**
사용자에게 **배송 요청 생성, 상태 관리, 추적 및 조회**하는 기능을 제공합니다.  

---

## 🛠️ **사용 기술 스택**
- **Backend**: `Java 21`, `Spring Boot 3.4.0`, `JPA (Hibernate)`
- **Database**: `MariaDB`, `MongoDB`
- **Cache**: `Redis`
- **Deployment**: `Docker`
- **Authentication**: `JWT`
- **Build**: `Gradle`

---

## 📋 **요구 사항**

### **구현 사항**
1. 배송 요청 생성
2. 배송 상태 업데이트
3. 배송 이력 조회

---

## 📊 **ERD**

아래는 배송 관리 서비스의 **ERD 설계**입니다:

---

## 🏗️ **인프라 설계**

서비스의 인프라는 다음과 같이 구성되어 있습니다:  

<img src="https://github.com/user-attachments/assets/0d151989-e0cd-4f82-9318-28e9791e1043" alt="delivery_service_infra" style="border: 1px solid #ddd; border-radius: 5px; width: 500px;">

### **인프라 설명**
- **Application Layer**: Spring Boot 기반의 애플리케이션 (MVC 패턴)
- **Database**: MariaDB와 MongoDB 사용
- **Cache**: Redis를 통한 동시성 문제 해결
- **Monitoring**: 

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
