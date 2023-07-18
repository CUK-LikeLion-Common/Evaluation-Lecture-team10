import styled from "styled-components";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";

const Likes = styled(motion.div)`
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
  color: #000;
  font-size: 15px;
`;

const LecContent = styled.div`
  color: #d9d9d9;
  font-size: 13px;
  margin-top: 10px;
  margin-left: 10px;
`;

const LecStar = styled.div`
  margin-right: 10px;
  display: flex;
  color: #efce4a;
  flex-flow: row nowrap;
  gap: 10px;
  //justify-content: space-evenly;
  span {
    margin-top: 5px;
    font-size: 20px;
    font-weight: 700;
  }
`;

function LikeBox({
  key,
  evaluationID,
  lectureName,
  professorName,
  totalScore,
  evaluationTitle,
  userID,
}) {
  const user_id = sessionStorage.getItem("user_id")
    ? sessionStorage.getItem("user_id")
    : null;
  const url = user_id ? `/${evaluationID}` : `/`;

  const onClick = () => {
    if (!user_id) {
      alert("로그인이 필요합니다");
    }
  };

  return (
    <Link
      to={url}
      onClick={onClick}
      style={{ textDecoration: "none", color: "inherit" }}
      state={{
        userID: userID,
      }}
    >
      <Likes whileHover={{ border: "3px solid #0c2e86", duration: 3 }}>
        <LecTitle>{lectureName}</LecTitle>
        <LecContent>{professorName} 교수님</LecContent>
        <LecStar>
          <LecTitle>{evaluationTitle}</LecTitle>
          <span>{totalScore}</span>
          <LecContent>{userID}</LecContent>
        </LecStar>
      </Likes>
    </Link>
  );
}

export default LikeBox;
