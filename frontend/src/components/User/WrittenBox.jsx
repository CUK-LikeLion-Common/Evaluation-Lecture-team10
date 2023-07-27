import styled from "styled-components";
import { FaStar } from "react-icons/fa";

const ARRAY = [0, 1, 2, 3, 4];

const stars = ARRAY.map((array) => <FaStar size="20" />);

const Likes = styled.div`
  margin-top: 10px;
  text-align: left;
  width: 850px;
  height: 220px;
  display: flex;
  flex-direction: column;
  border: 1px solid #000;
`;

const LecTitle = styled.div`
  margin-top: 15px;
  margin-left: 10px;
  font-size: 15px;
  color: #000;
`;

const LecContent = styled.div`
  color: #d9d9d9;
  font-size: 13px;
  margin-top: 10px;
  margin-left: 10px;
`;

const LecStar = styled.div`
  margin: 10px 0px 10px 10px;
  display: flex;
  color: #efce4a;
  //flex-flow: row nowrap;
  //justify-content: space-evenly;
  span {
    font-size: 20px;
    font-weight: 700;
  }
`;

const EditDelete = styled.div`
  //background-color: tomato;
  margin-top: 5px;
  height: 25px;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-end;
`;

const EditDeleteBtn = styled.button`
  background: #fff;
  margin-right: 5px;
  color: #d9d9d9;
  font-size: 15px;
  border: none;
`;

const WrittenBox = () => {
  return (
    <Likes>
      <LecTitle>강의명</LecTitle>
      <LecContent>000 교수님</LecContent>
      <LecStar>
        {stars}
        <span>5.0</span>
      </LecStar>
      <LecTitle>강의평가</LecTitle>
      <LecTitle>교수님 짱!</LecTitle>
      <EditDelete>
        <EditDeleteBtn>수정</EditDeleteBtn>
        <EditDeleteBtn>삭제</EditDeleteBtn>
      </EditDelete>
    </Likes>
  );
};

export default WrittenBox;
