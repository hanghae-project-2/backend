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

### **order 서비스**

사용자가 주문을 생성하고 관리할 수 있도록 하는 주문 생성, 수정, 조회 기능을 개발
주문 생성 이벤트를 Kafka를 통해 다른 서비스와 비동기로 통신하도록 구현

### **delivery 서비스**

주문 데이터를 기반으로 배송 정보를 처리하는 배송 생성, 수정, 삭제, 조회 기능을 구현
Kafka Consumer를 통해 order 서비스에서 발생한 이벤트를 실시간으로 수신하고 배송 상태를 업데이트하는 로직을 추가, 배송 생성 이벤트를 Kafka를 통해 다른 서비스와 비동기로 통신하도록 구현


### **2. 배송 상태 업데이트**

배송 상태를 `요청됨` → `배송 중` → `배송 완료` 순으로 업데이트

Redis를 통한 동시성 제어 및 Resilience4j로 Fallback 처리



---

## 🛠️ **트러블 슈팅**

### **1. 카프카 설정 문제**
- **문제**: Kafka Consumer를 설정하던 중 JsonDeserializer가 역직렬화할 대상 클래스를 찾지 못하면서 ClassNotFoundException이 발생했습니다.
- **해결**: Kafka 설정 파일인 KafkaConsumerConfig.java에 기타 다른 설정들을 아예 지운뒤 해당 코드만 남기고 yml 을 통해 최소 설정만 했더니 오히려 발생했던 에러가 사라졌습니다.

```java
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
```


### **2. 데이터 누적 문제**
- **문제**: 오래된 데이터로 인해 조회 성능 저하.  
- **해결**: 
  1. **MariaDB 인덱스 설정**  
  2. **MongoDB로 데이터 이관**  

---


## 🧩 **개선점**
- 메시지 큐(Kafka, RabbitMQ)를 사용해 이벤트 기반 시스템으로 개선.
- ElasticSearch를 활용하여 배송 이력 조회 성능 최적화.
- JPA Custom Repository 패턴 적용으로 코드 유연성 향상.

---

## 🎤 **소감**

### **혜정**  

> Test를 세분화해 미리 진행하지 않고 마지막에 몰아서 한 점이 부족하다 느껴졌고 또 다른 조원 분들에 비해 msa 나 docker 를 접해보지 못해 다루는데에 미숙했던 점이 보안되야할 점이라고 느껴집니다. </br>
> 짧은 시간동안 너무나 부족한 점을 많이 느끼고 또 공부의 필요성을 느꼈던 2주 였습니다. </br>
> 그동안 접해보지 못했던 새로운 기술들과 구조를 배울 수 있던 좋은 시간이었던거 같습니다!
> 다들 많이 도와주시고 알려주셔서 감사합니다!

