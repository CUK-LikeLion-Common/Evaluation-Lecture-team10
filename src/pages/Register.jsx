import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Section = styled.section`
  background-color: #ffffff; /* 배경색 설정 */
  width: 100%; /* 전체 화면 너비로 설정 */
  height: 100vh; /* 전체 화면 높이로 설정 */
  display: flex; /* 컨텐츠를 가로로 정렬하기 위해 flex 사용 */
  justify-content: center; /* 가로 정렬을 화면 가운데로 설정 */
  align-items: center; /* 세로 정렬을 화면 가운데로 설정 */
`;

const Header = styled.header`
  background-color: #0c2e86; /* 헤더 배경색 설정 */
  width: 100%; /* 최대 너비로 설정 */
  max-width: 1000px; /* 최대 너비를 1000px로 제한 */
  height: 60vh; /* 헤더 높이 설정 */
  position: relative; /* 내부 컨텐츠의 위치 설정을 위해 position 사용 */
  color: #ffffff; /* 텍스트 색상 설정 */
  font-family: Inter; /* 폰트 설정 */
  text-align: center; /* 텍스트를 왼쪽 정렬 */
  font-size: 30px; /* 폰트 크기 설정 */
  justify-content: space-between; /* 컨텐츠 사이에 여백 추가 */
  padding-top: 50px; /* 헤더 위쪽 여백 추가 */
`;

const FormContainer = styled.div`
  position: absolute; /* 내부 컨텐츠의 위치 설정을 위해 position 사용 */
  top: 50%; /* 상단 여백을 50%로 설정하여 수직 중앙 정렬 */
  left: 50%; /* 좌측 여백을 50%로 설정하여 수평 중앙 정렬 */
  transform: translate(-50%, -50%); /* 좌측과 상단으로 50%씩 이동하여 정확한 가운데 정렬 */

`;

const Label = styled.label`
  color: #bfbdbd; /* 라벨 글자색 설정 */
  width: 80px; /* 라벨 너비 설정 */
  height: 26px; /* 라벨 높이 설정 */
  font-family: Inter; /* 폰트 설정 */
  text-align: left; /* 텍스트를 왼쪽 정렬 */
  font-size: 20px; /* 폰트 크기 설정 */
  letter-spacing: 0; /* 글자 간격 설정 */
  margin-bottom: 10px; /* 버튼과 하단 문구 사이에 간격 추가 */

`;

const Input = styled.input`
  background-color: #ffffff; /* 입력 필드 배경색 설정 */
  width: 100%; /* 입력 필드 너비를 100%로 설정하여 부모 컨테이너에 맞게 조절 */
  height: 36px; /* 입력 필드 높이 설정 */
  border: 1px solid #bebebe; /* 입력 필드 테두리 설정 */
  margin-bottom: 30px; /* 하단 여백 설정 */
`;

const Button = styled.button`
  background-color: #011445; /* 버튼 배경색 설정 */
  width: 100%; /* 버튼 너비를 100%로 설정하여 부모 컨테이너에 맞게 조절 */
  height: 40px; /* 버튼 높이 설정 */
  color: #ffffff; /* 버튼 글자색 설정 */
  font-family: Inter; /* 폰트 설정 */
  text-align: center; /* 텍스트를 가운데 정렬 */
  font-size: 20px; /* 폰트 크기 설정 */
  letter-spacing: 0; /* 글자 간격 설정 */
  margin-bottom: 30px; /* 하단 여백 설정 */
  margin-top: 30px; /* 상단 여백 설정 */
  
`;

const Register = () => {
  return (
    <Section>
      <Header>
        <h1>회원가입</h1> {/* 헤더 제목 */}
      </Header>
      <FormContainer>
        <Label htmlFor="username">아이디</Label> {/* 아이디 라벨 */}
        <Input type="text" id="username" /> {/* 아이디 입력 필드 */}

        <Label htmlFor="password">비밀번호</Label> {/* 비밀번호 라벨 */}
        <Input type="password" id="password" /> {/* 비밀번호 입력 필드 */}

        <Label htmlFor="password-confirm">비밀번호 확인</Label> {/* 비밀번호 확인 라벨 */}
        <Input type="password" id="password-confirm" /> {/* 비밀번호 확인 입력 필드 */}

        <Button >회원가입</Button> {/* 로그인 버튼 */}

      </FormContainer>
    </Section>
  );
};

export default Register;