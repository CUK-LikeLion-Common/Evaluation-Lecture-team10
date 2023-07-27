import styled from "styled-components";
import WrittenBox from "../components/User/WrittenBox";

const ARRAY = [0, 1, 2];

const writtenLectures = ARRAY.map((array) => <WrittenBox />);

const Wrapper = styled.div`
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  font-family: Inter;
  font-weight: 400;
`;

const LinkBox = styled.div`
  width: 850px;
  height: 80px;
  text-align: left;
`;

const WrittenBtn = styled.button`
  background: ${(props) => props.bgColor};
  border: 1px solid #0c2e86;
  height: 60px;
  margin-right: 50px;
  font-size: 28px;
  color: ${(props) => props.fontColor};
`;

const WithdrawBox = styled.div`
  //background-color: teal;
  margin-top: 20px;
  width: 850px;
  height: 25px;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-end;
`;

const WithdrawBtn = styled.button`
  background: #fff;
  color: #f00;
  font-size: 16px;
  border: none;
`;

const User = () => {
  return (
    <Wrapper>
      <LinkBox>
        <WrittenBtn bgColor="#0c2e86" fontColor="#fff">
          내가 쓴 글
        </WrittenBtn>
        <WrittenBtn bgColor="#fff" fontColor="#000">
          내가 추천한 글
        </WrittenBtn>
      </LinkBox>
      {writtenLectures}
      <WithdrawBox>
        <WithdrawBtn>회원 탈퇴하기</WithdrawBtn>
      </WithdrawBox>
    </Wrapper>
  );
};

export default User;
