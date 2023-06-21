INSERT INTO svs.MEMBER(       
					USER_ID,	       
					USER_PW,        
					USER_NAME,    
					EMAIL, 
					PHONE,        
					ROLENAME,	    
					status)
  VALUES (
				'admin', 
				'{bcrypt}$2a$10$cbdnb4FRynrvG73gb7R2/Oo1wCXHGrob3XXApahP5RnbgKt9TgZou',
				'관리자',
				'admin@admin.com',
				'010-0000-0000',
				'ROLE_ADMIN',
				'ACTIVE');
				
INSERT INTO svs.MEMBER(       
					USER_ID,	       
					USER_PW,        
					USER_NAME,    
					EMAIL, 
					PHONE,        
					ROLENAME,	    
					status)
  VALUES (
				'user1', 
				'{bcrypt}$2a$10$/IpDCBZsOjKmGR1u5MUTzuuIadvax8wLYIJgcUYRbqaXjFVo6v15e',
				'김사장',
				'user@user.com',
				'010-0000-0000',
				'ROLE_USER',
				'ACTIVE');
 
 
 INSERT INTO svs.business (BUSINESS_NAME, BUSINESS_ADDRESS, TOTAL_ROOM, USER_ID, PHONE)
VALUES
  ('신영펜션', '강원 홍천군 서면 한치골길 75-12', 10, 'user1', '123123123'),
  ('초코키즈풀빌라', '경기 가평군 가평읍 호반로 2083-23', 10, 'user1', '123123123'),
  ('더원풀빌라', '경남 거제시 장목면 흥남길 60', 10, 'user1', '123123123'),
  ('화이트스톤풀빌라', '경남 밀양시 단장면 고례로 1308-13 9', 10, 'user1', '123123123'),
  ('키즈포레풀빌라', '경기 포천시 이동면 포화로 346-10', 10, 'user1', '123123123'),
  ('원더풀글램핑앤카라반', '경기 가평군 상면 임초밤안골로 117-27', 10, 'user1', '123123123'),
  ('루나키즈풀빌라', '경기 가평군 상면 돌아우길 42-26', 10, 'user1', '123123123'),
  ('빵빵키즈풀빌라', '경기 안산시 단원구 중우물길 5', 10, 'user1', '123123123'),
  ('삐삐키즈풀빌라&카라반글램핑', '경기 가평군 상면 임초밤안골로 115-45', 10, 'user1', '123123123'),
  ('평창리버힐', '강원 평창군 대화면 장미산길 23-33', 10, 'user1', '123123123'),
  ('바오키즈풀빌라', '경기 가평군 상면 돌아우길 61-25', 10, 'user1', '123123123'),
  ('이오스풀빌라AD', '경북 경주시 감포읍 동해안로 2706', 10, 'user1', '123123123'),
  ('솜솜키즈풀빌라', '경기 가평군 상면 둔덕말길 13-62', 10, 'user1', '123123123'),
  ('조이키즈풀빌라', '경기 가평군 상면 돌아우길 42-24', 10, 'user1', '123123123'),
  ('츠키노야료칸풀빌라', '경남 거제시 장목면 옥포대첩로 1084-4', 10, 'user1', '123123123'),
  ('뷰델카라반펜션', '경북 경주시 감포읍 감포로 329-34', 10, 'user1', '123123123');
   
INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 1, room_name
FROM (
	SELECT '로얄별' AS room_name UNION ALL
	SELECT '매화(애견동반 가능객실)' UNION ALL
	SELECT '샛별' UNION ALL
	SELECT '잉꼬(애견동반 가능객실)' UNION ALL
	SELECT '국화(애견동반 가능객실)' UNION ALL
	SELECT '봉황(애견동반 가능객실)' UNION ALL
	SELECT '수선화(애견동반 가능객실)' UNION ALL
	SELECT '라일락(애견동반 가능객실)' UNION ALL
	SELECT '백로(애견동반 가능객실)' UNION ALL
	SELECT '공작 (애견동반 가능객실)' UNION ALL
	SELECT '큰별' UNION ALL
	SELECT '십자매(애견동반 가능객실)' UNION ALL
	SELECT '목련(애견동반 가능객실)' UNION ALL
	SELECT '원앙(애견동반 가능객실)' UNION ALL
	SELECT '작은별' UNION ALL
	SELECT '장미(애견동반 가능객실)'
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 2, room_name
FROM(
	SELECT '샤샤풀빌라5' AS room_name UNION ALL
	SELECT '코코풀빌라4' UNION ALL
	SELECT '샤샤풀빌라1' UNION ALL
	SELECT '샤샤풀빌라4' UNION ALL
	SELECT '코코풀빌라5' UNION ALL
	SELECT '샤샤풀빌라2' UNION ALL
	SELECT '초코풀빌라3' UNION ALL
	SELECT '코코풀빌라1' UNION ALL
	SELECT '초코풀빌라4' UNION ALL
	SELECT '코코풀빌라2' UNION ALL
	SELECT '초코풀빌라2' UNION ALL
	SELECT '코코풀빌라6' UNION ALL
	SELECT '샤샤풀빌라6' UNION ALL
	SELECT '코코풀빌라3' UNION ALL
	SELECT '초코풀빌라1' UNION ALL
	SELECT '샤샤풀빌라3'
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 3, room_name
FROM(
	SELECT 'A-201' AS room_name UNION ALL
	SELECT 'B-301' UNION ALL
	SELECT 'A-301' UNION ALL
	SELECT 'A-101' UNION ALL
	SELECT 'B-101' UNION ALL
	SELECT 'B-201' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 4, room_name
FROM(
	SELECT '9동' AS room_name UNION ALL
	SELECT '10동' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 5, room_name
FROM(
	SELECT '당근방' AS room_name UNION ALL
	SELECT '스타방' UNION ALL
	SELECT '로켓방' UNION ALL
	SELECT '산호방' UNION ALL
	SELECT '레드밀방' UNION ALL
	SELECT '쿠키방' UNION ALL
	SELECT '스위트방' UNION ALL
	SELECT '마린방' UNION ALL
	SELECT '유니콘방' UNION ALL
	SELECT '아이스크림방' UNION ALL
	SELECT '서핑방' UNION ALL
	SELECT '포레방' UNION ALL
	SELECT '정글방' UNION ALL
	SELECT '도넛방' UNION ALL
	SELECT '옐로우핑크방' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 6, room_name
FROM(
	SELECT '원더풀 글램핑1(랜덤배정)' AS room_name UNION ALL
	SELECT '원더풀 일반카라반3(랜덤배정)' UNION ALL
	SELECT '원더풀 수영장카라반2(랜덤배정)' UNION ALL
	SELECT '원더풀 수영장카라반4(랜덤배정)' UNION ALL
	SELECT '원더풀 수영장카라반1(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반2(랜덤배정)' UNION ALL
	SELECT '원더풀 글램핑2(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반4(랜덤배정)' UNION ALL
	SELECT '원더풀 수영장카라반3(랜덤배정)' UNION ALL
	SELECT '당일바베큐' UNION ALL
	SELECT '원더풀 수영장카라반5(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반5(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반1(랜덤배정)' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 7, room_name
FROM(
	SELECT 'A 201 (침실2 화장실2 실내수영장)' AS room_name UNION ALL
	SELECT 'B 202호' UNION ALL
	SELECT '풀램 C타입' UNION ALL
	SELECT 'A 101호' UNION ALL
	SELECT 'B 103호' UNION ALL
	SELECT 'A 201호' UNION ALL
	SELECT '풀램 A타입' UNION ALL
	SELECT 'B 102호' UNION ALL
	SELECT '풀램 B타입' UNION ALL
	SELECT 'B 203호' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 8, room_name
FROM(
	SELECT '키즈풀빌라 4호' AS room_name UNION ALL
	SELECT '키즈풀빌라 6호 (독채)' UNION ALL
	SELECT '키즈풀빌라 1호 (독채)' UNION ALL
	SELECT '키즈풀빌라 2호' UNION ALL
	SELECT '키즈풀빌라 5호' UNION ALL
	SELECT '키즈풀빌라 3호' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 9, room_name
FROM(
	SELECT '아이스크림[침실2+화장실1]오후3시입실오전11시퇴실' AS room_name UNION ALL
	SELECT '원더풀 일반카라반3(랜덤배정)' UNION ALL
	SELECT '독채삐삐풀램핑6(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '산호방[침실3+화장실2]   오후1시입실 오전10시퇴실' UNION ALL
	SELECT '독채삐삐풀램핑5(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '팩토리[침실2+화장실2]오후1시입실 오전10시퇴실' UNION ALL
	SELECT '독채삐삐풀램핑4(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '스위트홈[침실2+화장실2]오후3시 입실오전11시퇴실' UNION ALL
	SELECT '독채삐삐풀램핑2(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '독채삐삐풀램핑1(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '기차[침실2+화장실1]오후3시입실오전11시퇴실' UNION ALL
	SELECT '원더풀 일반카라반5(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반1(랜덤배정)' UNION ALL
	SELECT '해적선[침실3+화장실2] 오후3시입실오전11시퇴실' UNION ALL
	SELECT '원더풀 수영장카라반3(랜덤배정)' UNION ALL
	SELECT '롤러코스터[침실2+화장실1]오후3시입실오전11시퇴실' UNION ALL
	SELECT '애견동반독채삐삐풀램핑2(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '원더풀 수영장카라반5(랜덤배정)' UNION ALL
	SELECT '애견동반독채삐삐풀램핑1(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '쿠키방[침실2+화장실1]오후3시입실오전11시퇴실' UNION ALL
	SELECT '원더풀 수영장카라반1(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반2(랜덤배정)' UNION ALL
	SELECT '원더풀 글램핑1(랜덤배정)' UNION ALL
	SELECT '독채삐삐풀램핑3(랜덤배정 평일특가 조식제공)' UNION ALL
	SELECT '원더풀 글램핑2(랜덤배정)' UNION ALL
	SELECT '원더풀 일반카라반4(랜덤배정)' UNION ALL
	SELECT '핑크밀[침실2+화장실2]오후3시입실오전11시퇴실' UNION ALL
	SELECT '원더풀 수영장카라반2(랜덤배정)' UNION ALL
	SELECT '원더풀 수영장카라반4(랜덤배정)' UNION ALL
	SELECT '삐삐바베큐' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 10, room_name
FROM(
	SELECT '노르딕 (애견가능)' AS room_name UNION ALL
	SELECT '풀빌라디럭스' UNION ALL
	SELECT '레트로' UNION ALL
	SELECT '다다오' UNION ALL
	SELECT '컨템포러리' UNION ALL
	SELECT '롯지캠프D' UNION ALL
	SELECT '티파니 (애견가능)' UNION ALL
	SELECT '롯지캠프C' UNION ALL
	SELECT '롯지캠프A' UNION ALL
	SELECT '파티오 (애견가능)' UNION ALL
	SELECT '롯지캠프B' UNION ALL
	SELECT '오드리 (애견가능)' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 11, room_name
FROM(
	SELECT 'A103(마린)' AS room_name UNION ALL
	SELECT 'B106(공룡)' UNION ALL
	SELECT 'A102(자동차)' UNION ALL
	SELECT 'B204(캐슬)' UNION ALL
	SELECT 'B104(자동차)' UNION ALL
	SELECT 'A202(캐슬)' UNION ALL
	SELECT 'B203(아이스크림)' UNION ALL
	SELECT 'B105(마린)' UNION ALL
	SELECT 'A101 (공룡)' UNION ALL
	SELECT 'A201(아이스크림)' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 12, room_name
FROM(
	SELECT 'D301 (침대1 루프탑수영장)' AS room_name UNION ALL
	SELECT 'D 202 (침대1 실내수영장)' UNION ALL
	SELECT 'D 102 ( 침대1 실내수영장)' UNION ALL
	SELECT 'D 101 (침대1 실내수영장)' UNION ALL
	SELECT 'A 201 (침실2 화장실2 실내수영장)' UNION ALL
	SELECT 'A 301 (침실2 화장실2 루프탑수영장 스파)' UNION ALL
	SELECT 'D 201 (침대1 실내수영장)' UNION ALL
	SELECT 'D302 (침대1 루프탑수영장)' UNION ALL
	SELECT 'A 101 (침실2 화장실2 실내수영장 스파)' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 13, room_name
FROM(
	SELECT '101호' AS room_name UNION ALL
	SELECT '글램핑A' UNION ALL
	SELECT '102호' UNION ALL
	SELECT '201호' UNION ALL
	SELECT '글램핑B' UNION ALL
	SELECT '202호' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 14, room_name
FROM(
	SELECT '쿠키키즈룸' AS room_name UNION ALL
	SELECT '토이키즈룸' UNION ALL
	SELECT '조이키즈룸' UNION ALL
	SELECT '우디키즈룸' UNION ALL
	SELECT '퍼피키즈룸' 
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 15, room_name
FROM(
	SELECT '202 히카리 (자쿠지)' AS room_name UNION ALL
	SELECT '201 츠키 (POOL, 히노끼)' UNION ALL
	SELECT '203 호시 (POOL, 히노끼)' UNION ALL
	SELECT '402 (스파, 복층)' UNION ALL
	SELECT '401 (스파, 복층)' UNION ALL
	SELECT '301 (스파, 더블트윈)'
)AS room_data;

INSERT INTO svs.rooms (BUSINESS_NUM, ROOM_NAME)
SELECT 16, room_name
FROM(
	SELECT 'A 럭셔리 일반 (오션뷰)' AS room_name UNION ALL
	SELECT 'B 럭셔리 일반 (오션뷰)' UNION ALL
	SELECT 'C 럭셔리 일반 (오션뷰)' UNION ALL
	SELECT 'D 럭셔리 일반 (오션뷰)' UNION ALL
	SELECT 'E 럭셔리 일반 (오션뷰)'
)AS room_data;
   