import styled from "styled-components";
import { FaStar } from "react-icons/fa";

const ARRAY = [0, 1, 2, 3, 4];

const stars = ARRAY.map((array) => <FaStar size="20" />);

const Likes = styled.div`
  margin-top: 10px;
  text-align: left;
  width: 300px;
  height: 115px;
  display: flex;
  flex-direction: column;
  border: 1px solid #000;
`;

const LecTitle = styled.div`
  margin-top: 10px;
  margin-left: 10px;
  font-size: 15px;
`;

const LecContent = styled.div`
  color: #d9d9d9;
  font-size: 13px;
  margin-top: 10px;
  margin-left: 10px;
`;

const LecStar = styled.div`
  margin-top: 20px;
  display: flex;
  color: #efce4a;
  flex-flow: row nowrap;
  justify-content: space-evenly;
  span {
    font-size: 20px;
    font-weight: 700;
  }
`;

const LikeBox = () => {
  return (
    <Likes>
      <LecTitle>강의명</LecTitle>
      <LecContent>000 교수님</LecContent>
      <LecStar>
        {stars}
        <span>5.0</span>
        <LecContent>작성자</LecContent>
      </LecStar>
    </Likes>
  );
};

export default LikeBox;
