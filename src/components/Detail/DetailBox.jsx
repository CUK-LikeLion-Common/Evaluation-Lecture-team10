import { AiTwotoneLike } from "react-icons/ai";
import styled from "styled-components";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

function DetailBox() {
  const { evaluationID } = useParams();
  const [evaluation, setEvaluation] = useState(null);
  const [likeCount, setLikeCount] = useState(0);

  const { state } = useLocation();
  const navigate = useNavigate();
  const user_id = sessionStorage.getItem("user_id")
    ? sessionStorage.getItem("user_id")
    : null;
  const user_password = sessionStorage.getItem("user_password")
    ? sessionStorage.getItem("user_password")
    : null;
  useEffect(() => {
    axios
      .get(`/evaluation/read/${evaluationID}`)
      .then((response) => {
        setEvaluation(response.data);
        //console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
        alert("글을 불러오는데 실패했습니다.");
      });
  }, [evaluationID]);

  const user_write = state ? state.userID : "";

  if (!evaluation) {
    return <p>loading...</p>;
  }

  // 글 추천
  const handleLike = () => {
    const data = {
      userID: "",
      userPassword: "",
      userEmail: "",
    };

    axios
      .post(`/likey/${evaluationID}`, data)
      .then((response) => {
        alert("글을 추천하였습니다.");
        setLikeCount(likeCount + 1);
      })
      .catch((error) => {
        console.error(error);
        alert("추천에 실패했습니다.");
      });
  };

  // 글 삭제
  const handleDelete = () => {
    if (user_id === user_write) {
      const data = {
        userID: user_id,
        userPassword: user_password,
      };
      const confirmed = window.confirm("정말로 삭제하시겠습니까?");
      if (confirmed) {
        axios
          .delete(`/evaluation/delete/${evaluationID}`, { data })
          .then(() => {
            alert("삭제되었습니다.");
            navigate("/");
          })
          .catch((error) => {
            console.error(error);
            alert("삭제에 실패했습니다.");
          });
      }
    } else {
      alert("자신의 글만 삭제할 수 있습니다.");
    }
  };

  //글 수정
  const handleModify = () => {
    if (state.userID === user_id) {
    } else {
      alert("자신의 글만 수정할 수 있습니다.");
    }
  };

  return (
    <Wrapper>
      <FlexRow>
        <Label>강의</Label>
        <Value>{evaluation.result.message.lectureName}</Value>
      </FlexRow>

      <FlexRow>
        <Label>교수</Label>
        <Value>{evaluation.result.message.professorName}</Value>
      </FlexRow>

      <FlexRow>
        <Label>수강 연도</Label>
        <Value>{evaluation.result.message.lectureYear}</Value>
      </FlexRow>

      <FlexRow>
        <Label>학기</Label>
        <Value>{evaluation.result.message.semesterDivide}</Value>
      </FlexRow>

      <FlexRow>
        <Label>강의 구분</Label>
        <Value>{evaluation.result.message.lectureDivide}</Value>
      </FlexRow>

      <FlexRow>
        <Label>제목</Label>
        <Value>{evaluation.result.message.evaluationTitle}</Value>
      </FlexRow>

      <FlexRow>
        <Label>내용</Label>
        <Value>{evaluation.result.message.evaluationContent}</Value>
      </FlexRow>

      <FlexRow>
        <Label>총 점수</Label>
        <Value>{evaluation.result.message.totalScore}</Value>
      </FlexRow>

      <FlexRow>
        <Label>성적</Label>
        <Value>{evaluation.result.message.creditScore}</Value>
      </FlexRow>

      <FlexRow>
        <Label>편의성</Label>
        <Value>{evaluation.result.message.comfortableScore}</Value>
      </FlexRow>

      <FlexRow>
        <Label>평가 점수</Label>
        <Value>{evaluation.result.message.lectureScore}</Value>
      </FlexRow>

      <ReviewWrapper>
        <AiTwotoneLike size="20" color="#b9ccfa" />
        <ReviewText onClick={handleLike}>추천</ReviewText>
        <LikeCount>{likeCount}</LikeCount>
      </ReviewWrapper>

      <ButtonContainer>
        {/* <Button
          to={`/modify/${evaluation.result.message.evaluationID}`}
          state={evaluation.result.message}
        >
          수정
        </Button> */}
        {
          user_write === user_id ? (
            <Button
              to={`/modify/${evaluation.result.message.evaluationID}`}
              state={evaluation.result.message}
            >
              수정
            </Button>
          ) : (
            <Button onClick={handleModify}>수정</Button>
          )
          //navigate(`/modify/${evaluation.result.message.evaluationID}`);
        }

        <Button onClick={handleDelete}>삭제</Button>
      </ButtonContainer>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  border: 1px solid #0c2e86;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  width: 600px;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 70px;
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 15px;
`;

export const Button = styled(Link)`
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
  width: 150px;
`;

const Value = styled.div`
  width: 400px;
`;

const FlexRow = styled.div`
  display: flex;
  align-items: center;
  text-align: center;
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

const LikeCount = styled.div`
  font-size: 15px;
  margin-left: 10px;
`;

export default DetailBox;
