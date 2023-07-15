import { AiTwotoneLike } from "react-icons/ai";
import styled from "styled-components";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

const DetailBox = () => {
  const { evaluationID } = useParams();
  const [evaluation, setEvaluation] = useState(null);

  useEffect(() => {
    axios
      .get(`/evaluation/read/${evaluationID}`)
      .then((response) => {
        setEvaluation(response.data);
      })
      .catch((error) => {
        console.error(error);
        alert("글을 불러오는데 실패했습니다.");
      });
  }, [evaluationID]);

  if (!evaluation) {
    return <p>loading...</p>;
  }

  //console.log(evaluation.result.message);

  // 글 추천
  const handleRecommendation = () => {
    const requestData = {
      userID: "",
      userPassword: "",
      userEmail: "",
    };

    axios
      .post(`/likey/${evaluationID}`, requestData)
      .then((response) => {
        if (response.data.success) {
          alert("글이 추천되었습니다!");
        } else {
          alert("글 추천에 실패했습니다.");
        }
      })
      .catch((error) => {
        console.error(error);
        alert("글 추천에 실패했습니다.");
      });
  };

  return (
    <Wrapper>
      <ButtonContainer>
        <Button>수정</Button>
        <Button>삭제</Button>
      </ButtonContainer>

      <div>
        <Label>{evaluation.result.message.lectureName}</Label>
        <ProfessorWrapper>
          {evaluation.result.message.professorName}
        </ProfessorWrapper>
      </div>

      <div>
        <div>수강 연도</div>
        <div>{evaluation.result.message.lectureYear}</div>
      </div>

      <div>
        <div>학기</div>
        <div>{evaluation.result.message.semesterDivide}</div>
      </div>

      <div>
        <div>강의 구분</div>
        <div>{evaluation.result.message.lectureDivide}</div>
      </div>

      <div>
        <div>평가 글 제목</div>
        <div>{evaluation.result.message.evaluationTitle}</div>
      </div>

      <div>
        <div>내용</div>
        <div>{evaluation.result.message.evaluationContent}</div>
      </div>

      <div>
        <div>총 점수</div>
        <div>{evaluation.result.message.totalScore}</div>
      </div>

      <div>
        <div>성적</div>
        <div>{evaluation.result.message.creditScore}</div>
      </div>

      <div>
        <div>강의가 어느정도 널널한가?</div>
        <div>{evaluation.result.message.comfortableScrore}</div>
      </div>

      <div>
        <div>평가 점수</div>
        <div>{evaluation.result.message.lectureScore}</div>
      </div>

      <ReviewWrapper>
        <AiTwotoneLike size="20" color="#b9ccfa" />
        <ReviewText onClick={handleRecommendation}>추천</ReviewText>
      </ReviewWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  border: 1px solid #0c2e86;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  width: 100%;
  max-width: fit-content;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 70px;
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-left: 10px;
`;

export const Button = styled.div`
  margin: 10px;
  color: gray;
  font-size: 12px;
  &:hover {
    color: #011445;
  }
`;

const Label = styled.div`
  text-align: left;
  margin-bottom: 20px;
  font-size: 18px;
  font-weight: 600;
  margin-right: 40px;
`;

const ProfessorWrapper = styled.div`
  text-align: left;
  margin-bottom: 20px;
  font-size: 16px;
`;

const ReviewWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 30px;
  background-color: #011445;
  border: none;
  color: white;
  padding: 5px 10px;
  cursor: pointer;
  width: 80px;
`;

const ReviewText = styled.div`
  margin-left: 5px;
`;

export default DetailBox;
