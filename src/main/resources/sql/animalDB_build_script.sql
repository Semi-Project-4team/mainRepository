DROP TABLE IF EXISTS tbl_person CASCADE;
DROP TABLE IF EXISTS tbl_pet CASCADE;

DROP TABLE IF EXISTS tbl_person_review CASCADE;
DROP TABLE IF EXISTS tbl_main_subject CASCADE;
DROP TABLE IF EXISTS tbl_sido CASCADE;
DROP TABLE IF EXISTS tbl_sigungu CASCADE;
DROP TABLE IF EXISTS tbl_eupmyeondong CASCADE;
DROP TABLE IF EXISTS tbl_hospital CASCADE;
DROP TABLE IF EXISTS tbl_reserve CASCADE;
DROP TABLE IF EXISTS tbl_notice CASCADE;

DROP TABLE IF EXISTS tbl_favorite CASCADE;

-- 2. 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_person
(
    person_code INT NOT NULL AUTO_INCREMENT COMMENT '개인코드',
    person_name VARCHAR(10) NOT NULL COMMENT '개인회원명',
    person_email VARCHAR(20) NOT NULL COMMENT '개인회원이메일',
    person_password VARCHAR(100) NOT NULL COMMENT '개인회원비밀번호',
    person_birth DATE NOT NULL COMMENT '개인회원생년월일',
    person_phone_number VARCHAR(15) NOT NULL COMMENT '개인회원전화번호',
    person_information_collection VARCHAR(1) NOT NULL COMMENT '정보수집동의확인',

    CONSTRAINT pk_person_code PRIMARY KEY (person_code)
) ENGINE=INNODB COMMENT '개인회원';

CREATE TABLE IF NOT EXISTS tbl_pet
(
    pet_person_code INT NOT NULL AUTO_INCREMENT COMMENT '반려동물코드',
    person_code INT NOT NULL COMMENT '개인코드',
    pet_name VARCHAR(50) NOT NULL COMMENT '반려동물이름',
    pet_type VARCHAR(10) NOT NULL COMMENT '반려동물종',
    pet_birth VARCHAR(10) NOT NULL COMMENT '반려동물생년월일',
    pet_vaccination VARCHAR(1) NOT NULL COMMENT '반려동물예방접종',
    pet_chip VARCHAR(10) NOT NULL COMMENT '반려동물내장칩',
    pet_profile VARCHAR(10) NOT NULL COMMENT '반려동물사진',
    pet_gender VARCHAR(5) COMMENT '반려동물성별',

    CONSTRAINT pk_pet_person_code PRIMARY KEY (pet_person_code),
    CONSTRAINT fk_person_code FOREIGN KEY (person_code) REFERENCES tbl_person(person_code)
) ENGINE=INNODB COMMENT '반려동물';

CREATE TABLE IF NOT EXISTS tbl_sido
(
    -- column level constraints
    sido_code INT AUTO_INCREMENT COMMENT '시도코드',
    sido_name VARCHAR(10) NOT NULL COMMENT '시도',
    -- table level constraintcategory_codecategory_codes
    CONSTRAINT pk_sido_code PRIMARY KEY (sido_code)
) ENGINE=INNODB COMMENT '시/도';

CREATE TABLE IF NOT EXISTS tbl_sigungu
(
    sigungu_code varchar(5) COMMENT '시군구코드',
    sigungu_name VARCHAR(10) NOT NULL COMMENT '시군구',
    sido_code INT NOT NULL COMMENT '시도코드',
    -- table level constraints
    CONSTRAINT pk_sigungu_code PRIMARY KEY (sigungu_code),
    CONSTRAINT fk_sido_code FOREIGN KEY (sido_code) REFERENCES tbl_sido(sido_code)
) ENGINE=INNODB COMMENT '시/군/구';

CREATE TABLE IF NOT EXISTS tbl_eupmyeondong
(
    -- column level constraints
    eupmyeondong_code varchar(5) COMMENT '읍면동코드',
    eupmyeondong_name VARCHAR(10) NOT NULL COMMENT '읍면동',
    sigungu_code varchar(5) NOT NULL COMMENT '시군구코드',
    -- table level constraints
    CONSTRAINT pk_eupmyeondong_code PRIMARY KEY (eupmyeondong_code),
    CONSTRAINT fk_sigungu_code FOREIGN KEY (sigungu_code) REFERENCES tbl_sigungu(sigungu_code)
) ENGINE=INNODB COMMENT '읍/면/동';

CREATE TABLE IF NOT EXISTS tbl_main_subject
(
    -- column level constraints
    subject_code varchar(5) COMMENT '진료코드',
    subject_name VARCHAR(10) NOT NULL COMMENT '과목이름',
    -- table level constraints
    CONSTRAINT pk_subject_code PRIMARY KEY (subject_code)
) ENGINE=INNODB COMMENT '진료과목';

CREATE TABLE IF NOT EXISTS tbl_hospital
(
    -- column level constraints
    hospital_code INT AUTO_INCREMENT COMMENT '병원코드',

    subject_code varchar(5) NOT NULL COMMENT '진료코드', --
    eupmyeondong_code varchar(5) COMMENT '읍면동코드',

    hospital_name varchar(20) NOT NULL COMMENT '병원이름', --
    hospital_permit_number varchar(15) NOT NULL COMMENT '병원인가번호', --
    hospital_email varchar(30) NOT NULL COMMENT '병원이메일', --
    hospital_password varchar(100) NOT NULL COMMENT '병원비밀번호', --
    hospital_information_collection varchar(1) NOT NULL COMMENT '정보수집동의확인', --
    hospital_time DATE COMMENT '병원진료시간',
    hospital_detail_address varchar(10) COMMENT '병원세부소재지',
    hospital_intro_text varchar(50) COMMENT '병원소개글',
    hospital_phone_number varchar(10) NOT NULL COMMENT '병원전화번호', --
    hospital_photo varchar(10) COMMENT '병원사진',
    -- table level constraints
    CONSTRAINT pk_hospital_code PRIMARY KEY (hospital_code),
    CONSTRAINT fk_eupmyeondong_code FOREIGN KEY (eupmyeondong_code) REFERENCES tbl_eupmyeondong (eupmyeondong_code),
    CONSTRAINT fk_subject_code FOREIGN KEY (subject_code) REFERENCES tbl_main_subject (subject_code)
) ENGINE=INNODB COMMENT '병원';

CREATE TABLE IF NOT EXISTS tbl_person_review(
                                                person_code int NOT NULL COMMENT '개인코드',
                                                hospital_code int NOT NULL COMMENT '병원코드',
                                                review_write_date DATE NOT NULL COMMENT '리뷰작성일자',
                                                review_modify_date DATE NOT NULL COMMENT '리뷰수정일자',
                                                review_score int NOT NULL COMMENT '리뷰점수',
                                                review_photo VARCHAR(10) NOT NULL COMMENT '리뷰사진',
                                                review_explanation VARCHAR(100) NOT NULL COMMENT '리뷰내용',

    -- 복합 기본키 설정
                                                PRIMARY KEY (person_code, hospital_code),

    -- 외래키 설정
                                                CONSTRAINT fk_person_code1 FOREIGN KEY (person_code) REFERENCES tbl_person (person_code),
                                                CONSTRAINT fk_hospital_code1 FOREIGN KEY (hospital_code) REFERENCES tbl_hospital (hospital_code)

)ENGINE=INNODB COMMENT '리뷰';

CREATE TABLE IF NOT EXISTS tbl_reserve (
                                           person_code int NOT NULL COMMENT '개인코드',
                                           hospital_code int NOT NULL COMMENT '병원코드',
                                           reserve_time TIME NOT NULL COMMENT '방문시간',
                                           reserve_date DATE NOT NULL COMMENT '방문날짜',
                                           reserve_text VARCHAR(100) NULL COMMENT '전달사항',
                                           reserve_information_collection VARCHAR(1)  NOT NULL COMMENT '정보수집동의',

    -- 복합 기본키 설정
                                           PRIMARY KEY (person_code, hospital_code),

    -- 외래키 설정
                                           CONSTRAINT fk_person_code2 FOREIGN KEY (person_code) REFERENCES tbl_person (person_code),
                                           CONSTRAINT fk_hospital_code2 FOREIGN KEY (hospital_code) REFERENCES tbl_hospital (hospital_code)
) ENGINE=INNODB COMMENT '예약';

CREATE TABLE IF NOT EXISTS tbl_favorite
(
    person_code INT NOT NULL COMMENT '개인코드',
    favorite_name VARCHAR(10) NOT NULL COMMENT '즐겨찾기이름',
    CONSTRAINT fk_person_code3 FOREIGN KEY (person_code) REFERENCES tbl_person(person_code)
) ENGINE=INNODB COMMENT '즐겨찾기';

CREATE TABLE IF NOT EXISTS tbl_notice
(
    notice_code INT AUTO_INCREMENT COMMENT '공지사항코드',
    notice_name VARCHAR(255) NOT NULL COMMENT '공지사항이름',
    notice_date date NOT NULL COMMENT '공지사항작성날짜',
    notice_views INT NOT NULL COMMENT '공지사항조회수',
    notice_text VARCHAR(255) NOT NULL COMMENT '공지사항내용',

    CONSTRAINT pk_notice_code PRIMARY KEY (notice_code)
) ENGINE=INNODB COMMENT '공지사항';


-- 3. 데이터 삽입
INSERT INTO
    tbl_sido (sido_code, sido_name)
VALUES
    ('1', '서울'),
    ('2', '경기');

# SET @sido_id = LAST_INSERT_ID();

INSERT INTO
    tbl_sigungu (sigungu_code, sigungu_name, sido_code)
VALUES
    ('1', '강남구', '1'),
    ('2', '영등포구', '1'),
    ('3', '마포구', '1'),
    ('4', '화성시', '2'),
    ('5', '용인시기흥구', '2'),
    ('6', '수원시팔달구', '2');

INSERT INTO
    tbl_eupmyeondong (eupmyeondong_code, eupmyeondong_name, sigungu_code)
VALUES
    ('1', '개포동', '1'),
    ('2', '논현동', '1'),
    ('3', '대치동', '1'),

    ('4', '신길동', '2'),
    ('5', '양평동', '2'),
    ('6', '영등포동', '2'),

    ('7', '망원동', '3'),
    ('8', '마포동', '3'),
    ('9', '상암동', '3'),

    ('10', '능동', '4'),
    ('11', '반송동', '4'),
    ('12', '기안동', '4'),

    ('13', '신갈동', '5'),
    ('14', '상갈동', '5'),
    ('15', '구갈동', '5'),

    ('16', '인계동', '6'),
    ('17', '지동', '6'),
    ('18', '매교동', '6');

INSERT INTO
    tbl_main_subject (subject_code, subject_name)
VALUES
    ('1','안과'),
    ('2', '피부과'),
    ('3', '내과'),
    ('4', '정형외과'),
    ('5', '치과'),
    ('6','비뇨기과');

INSERT INTO
    tbl_hospital
(
    hospital_code,
    subject_code,
    eupmyeondong_code,
    hospital_name,
    hospital_permit_number,
    hospital_email,
    hospital_password,
    hospital_information_collection,
    hospital_time,
    hospital_detail_address,
    hospital_intro_text,
    hospital_phone_number,
    hospital_photo
)
VALUES
    -- 예제 데이터 삽입 (기본값을 VARCHAR로 변경)
    (NULL, '1', 1, '눈편한병원', '123456789', 'eye@gmail.com', 'eye1234', 'Y', '2024-01-01', '개포동 660-4', '눈이 편해지는 병원입니다.', '02-000-000', '/Users'),
    (NULL, '2', 2, '피부케어병원', '987654321', 'skin1@gmail.com', 'skin1234', 'Y', '2024-01-01', '논현동 660-4', '피부를 건강하게!', '02-000-000', '/Users'),
    (NULL, '3', 3, '피부케어병원', '123987456', 'skin2@gmail.com', 'skin1234', 'Y', '2024-01-01', '대치동 660-4', '피부를 건강하게!', '02-000-000', '/Users'),
    (NULL, '3', 4, '내과병원', '456123789', 'internal1@gmail.com', 'internal1234', 'Y', '2024-01-01', '신길동 660-4', '내과 진료 전문 병원.', '02-000-000', '/Users'),
    (NULL, '3', 5, '내과병원', '789456123', 'internal2@gmail.com', 'internal1234', 'Y', '2024-01-01', '양평동 660-4', '내과 진료 전문 병원.', '02-000-000', '/Users'),
    (NULL, '4', 6, '뼈튼튼병원', '321654987', 'orthopedics1@gmail.com', 'ortho1234', 'Y', '2024-01-01', '영등포동 660-4', '뼈 건강 전문 병원.', '02-000-000', '/Users'),
    (NULL, '4', 7, '뼈튼튼병원', '654789321', 'orthopedics2@gmail.com', 'ortho1234', 'Y', '2024-01-01', '망원동 660-4', '뼈 건강 전문 병원.', '02-000-000', '/Users'),
    (NULL, '5', 8, '튼튼이치과', '789321654', 'dental1@gmail.com', 'dental1234', 'Y', '2024-01-01', '마포동 660-4', '튼튼한 치아를 위해.', '02-000-000', '/Users'),
    (NULL, '6', 9, '소변건강병원', '456987321', 'urology1@gmail.com', 'urology1234', 'Y', '2024-01-01', '상암동 660-4', '비뇨기 건강 전문 병원.', '02-000-000', '/Users');
#         (NULL, 7, 10, '수술전문병원', '321789654', 'surgery1@gmail.com', 'surgery1234', 'Y', '2024-01-01', '능동 660-4', '외과 수술 전문 병원.', '/Users'),
#         (NULL, 7, 11, '수술전문병원', '987321654', 'surgery2@gmail.com', 'surgery1234', 'Y', '2024-01-01', '반송동 660-4', '외과 수술 전문 병원.', '/Users'),
#         (NULL, 8, 12, '산부인과병원', '654123987', 'obgyn1@gmail.com', 'obgyn1234', 'Y', '2024-01-01', '기안동 660-4', '산부인과 진료 전문.', '/Users'),
#         (NULL, 9, 13, '응급진료병원', '789654123', 'emergency1@gmail.com', 'emergency1234', 'Y', '2024-01-01', '신갈동 660-4', '응급 상황에 대비.', '/Users'),
#         (NULL, 9, 14, '응급진료병원', '321456789', 'emergency2@gmail.com', 'emergency1234', 'Y', '2024-01-01', '상갈동 660-4', '응급 상황에 대비.', '/Users'),
#         (NULL, 10, 15, '종양케어병원', '654789123', 'oncology1@gmail.com', 'oncology1234', 'Y', '2024-01-01', '구갈동 660-4', '종양 치료 전문.', '/Users'),
#         (NULL, 11, 16, '재활병원', '987123456', 'rehab1@gmail.com', 'rehab1234', 'Y', '2024-01-01', '인계동 660-4', '재활 치료 전문 병원.', '/Users'),
#         (NULL, 12, 17, '통증케어병원', '456321789', 'paincare1@gmail.com', 'paincare1234', 'Y', '2024-01-01', '지동 660-4', '통증 관리 전문.', '/Users'),
#         (NULL, 13, 18, '눈건강병원', '123654987', 'eye2@gmail.com', 'eye1234', 'Y', '2024-01-01', '매교동 660-4', '눈 건강 진료.', '/Users');COMMIT;

INSERT INTO
    tbl_person (person_code, person_name, person_email, person_password, person_birth, person_phone_number, person_information_collection)
VALUES
    (1, '이나라', 'dlskfk0513@naver.com', 'dlskfk01', now(), '010-0000-0000', 'Y'),
    (2, '백중윤', 'qorwnddbs@naver.com', 'qorwnddbs01', now(), '010-1111-1111', 'Y');
#     (3, '빵빵이', 'bbang@naver.com', 'bbang', now(), '010-2222-2222', 'Y');

INSERT INTO
    tbl_reserve
(
    person_code,
    hospital_code,
    reserve_time,
    reserve_date,
    reserve_text,
    reserve_information_collection)
VALUES
    (1,1,now(),now(),'hi','Y');

INSERT INTO
    tbl_person_review
VALUES
    (1,1,now(),now(),5,'abcd','abcd');

INSERT INTO
    tbl_notice (notice_name, notice_date, notice_views, notice_text)
VALUES
    ('추석 연휴 안내', '2024-09-01', 123, '추석 연휴 기간 동안 병원 휴무일을 안내드립니다. 자세한 일정은 홈페이지를 참고해 주세요.'),
    ('정기 시스템 점검', '2024-09-02', 156, '시스템 점검으로 인해 9월 5일 00:00부터 02:00까지 서비스 이용이 제한됩니다. 불편을 끼쳐 드려 죄송합니다.'),
    ('신규 의료 장비 도입', '2024-09-03', 98, '최신 의료 장비가 도입되었습니다. 더 나은 진료 서비스를 제공하기 위해 항상 노력하겠습니다.'),
    ('의료 상담 시간 변경', '2024-09-04', 76, '의료 상담 시간이 10월 1일부터 변경됩니다. 오전 상담 시간이 9시로 조정되니 예약에 참고해 주시기 바랍니다.'),
    ('9월 진료 일정 안내', '2024-09-05', 45, '9월 한 달간의 진료 일정을 안내드립니다. 자세한 사항은 공지사항을 확인해 주세요.'),
    ('코로나19 예방 접종 안내', '2024-09-06', 189, '코로나19 예방 접종이 가능합니다. 사전 예약을 통해 접종 일정을 잡아 주시기 바랍니다.'),
    ('정기 세미나 개최', '2024-09-07', 34, '다음 주에 정기 세미나가 개최됩니다. 이번 세미나는 반려동물 건강 관리에 관한 내용입니다. 많은 관심 부탁드립니다.'),
    ('병원 이전 안내', '2024-09-08', 212, '저희 병원이 새로운 장소로 이전합니다. 새 주소는 다음과 같으며, 10월 1일부터 새롭게 진료를 시작합니다.'),
    ('온라인 상담 시스템 도입', '2024-09-09', 67, '비대면 진료 및 상담을 위한 온라인 시스템이 도입되었습니다. 자세한 이용 방법은 홈페이지를 참고해 주세요.'),
    ('설 연휴 진료 안내', '2024-09-10', 150, '설 연휴 기간 중 진료 일정을 안내드립니다. 긴급 상황 발생 시에는 응급 진료가 가능합니다.');

INSERT INTO
    tbl_pet (pet_person_code, person_code, pet_name, pet_type, pet_birth, pet_vaccination, pet_chip, pet_profile, pet_gender)
VALUES
    (1, 1, '꼼이', '갱얼쥐', '000000', 'N', '000000', 'KHGFKF', '여'),
    (2, 2, '오곡', '고앵이', '000000', 'Y', '000000', 'KHGFKF', '남'),
    (3, 3, '모밀이', '갱얼쥐', '000000', 'Y', '000000', 'KHGFKF', '남');

INSERT INTO
    tbl_favorite (person_code, favorite_name)
VALUES
    (1, '꼼이건강검진병원'),
    (2, '오곡이'),
    (3, '모밀이병원');