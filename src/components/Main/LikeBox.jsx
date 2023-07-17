import styled from "styled-components";
import { FaStar } from "react-icons/fa";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";
import axios from "axios";

axios.get("/evaluation/read").then((response) => {
  //console.log(response);
});

//const ARRAY = [0, 1, 2, 3, 4];

//const stars = ARRAY.map((array) => <FaStar size="20" />);

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
  const url = `/${evaluationID}`;
  return (
    <Link to={url} style={{ textDecoration: "none", color: "inherit" }}>
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
