import styled from "styled-components";
import StarRating from "./StarRating";

const WriteBox = () => {
  // 글쓰기 등록시 onclick 이벤트 (지금은 alert만 구현)
  const WriteBtn = () => {
    alert("등록되었습니다");
  };

  return (
    <Wrapper>
      <div>
        <Label>강의명</Label>
        <Input placeholder="강의명을 입력하세요" />
      </div>

      <div>
        <Label>교수</Label>
        <Input placeholder="교수님 이름을 입력하세요" />
      </div>

      <StarRatingWrapper>
        <StarRating />
      </StarRatingWrapper>

      <div>
        <Label>강의 평가</Label>
        <Textarea placeholder="강의에 대한 자세한 평가를 해주세요 (최대 5줄 가능)" />
      </div>

      <div>
        <Button onClick={() => WriteBtn()}>등록</Button>
      </div>
    </Wrapper>
  );
};

export const Wrapper = styled.div`
  border: 1px solid #0c2e86;
  text-align: center;
  display: flex;
  flex-direction: column;
  padding: 30px;
  width: 100%;
  max-width: fit-content;
  margin: 0 auto;
  margin-top: 70px;
  margin-bottom: 50px;
`;

export const Label = styled.div`
  text-align: left;
`;

export const Input = styled.input`
  text-align: left;
  width: 600px;
  height: 20px;
  margin-top: 10px;
  margin-bottom: 30px;
  padding: 5px;
`;

export const StarRatingWrapper = styled.div`
  text-align: left;
  margin-bottom: 30px;
`;

export const Textarea = styled.textarea`
  text-align: left;
  width: 600px;
  height: 200px;
  margin-top: 10px;
  margin-bottom: 30px;
  padding: 5px;
`;

export const Button = styled.button`
  background-color: #011445;
  color: #ffffff;
  padding: 5px;
  width: 150px;
  //border-radius: 10px;
`;

export default WriteBox;
