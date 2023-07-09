import { FaStar } from "react-icons/fa";
import { AiTwotoneLike } from "react-icons/ai";
import styled from "styled-components";

const DetailBox = () => {
  // 추천 버튼 클릭시 나오는 alert
  const ReviewBtn = () => {
    alert("추천되었습니다!");
    // 추후에 confirm 창으로 취소/확인 버튼으로 수정할 예정
  };
  return (
    <Wrapper>
      <ButtonContainer>
        <Button>수정</Button>
        <Button>삭제</Button>
      </ButtonContainer>

      <LabelWrapper>
        <Label>영화로 보는 한국사</Label>
        <ProfessorWrapper>장미애</ProfessorWrapper>
      </LabelWrapper>

      <StarWrapper>
        <StarLabel>별점 (4.0)</StarLabel>
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="gray" />
      </StarWrapper>

      <div>
        <DetailLabel>강의 평가</DetailLabel>
        <DetailWrapper>
          과제가 없어서 꿀 강의긴 한데 시험이 진짜 어렵습니다. 중간도 어렵고
          기말은 더 어려워요. 3시간 풀강하시고 가끔 넘어서까지
          수업하십니다...보여주시는 영화도 재밌고 수업도 열정적이시긴 합니다.
          한국사에 좀 관심 많고 수업만 제대로 들어도 성적 잘 나옵니다. (저도
          그랬어요) 시험 문제에 영화 장면 캡처해서 나오니까 주의하세요!
        </DetailWrapper>
      </div>

      <ReviewWrapper>
        <AiTwotoneLike size="20" color="#b9ccfa" />
        <ReviewText onClick={() => ReviewBtn()}>추천</ReviewText>
      </ReviewWrapper>
    </Wrapper>
  );
};

export const Wrapper = styled.div`
  border: 1px solid #0c2e86;
  text-align: center;
  display: flex;
  flex-direction: column;
  //align-items: center;
  padding: 30px;
  max-width: fit-content;
  margin: 0 auto;
  margin-top: 30px;
  margin-bottom: 30px;
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

const LabelWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

export const Label = styled.div`
  text-align: left;
  margin-bottom: 20px;
  font-size: 18px;
  font-weight: 600;
  margin-right: 40px;
`;

export const ProfessorWrapper = styled.div`
  text-align: left;
  margin-bottom: 20px;
  font-size: 16px;
`;

export const StarWrapper = styled.div`
  text-align: left;
  margin-bottom: 30px;
`;

export const StarLabel = styled.div`
  text-align: left;
  margin-bottom: 10px;
  font-size: 18px;
`;

export const DetailLabel = styled.div`
  text-align: left;
  margin-bottom: 10px;
  font-size: 18px;
  text-decoration: underline;
  text-underline-position: under;
`;

export const DetailWrapper = styled.div`
  text-align: left;
`;

export const ReviewWrapper = styled.div`
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

export const ReviewText = styled.div`
  margin-left: 5px;
`;

export default DetailBox;
