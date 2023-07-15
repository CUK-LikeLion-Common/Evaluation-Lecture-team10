import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const WriteBox = () => {
  // 로그인 여부 판단
  //const [user, setUser] = useState(null);

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    evaluationID: "",
    userID: "",
    lectureName: "", // 강의명
    professorName: "", // 교수명
    lectureYear: "", // 강의년도
    semesterDivide: "", // 학기 구분
    lectureDivide: "", // 강의 구분 (전공, 교양, 기타)
    evaluationTitle: "", // 강의 평가 제목
    evaluationContent: "", // 강의평가 내용
    totalScore: "", // 총 점수
    creditScore: "", // 성적 점수
    comfortableScore: "", // 강의 널널한 정도
    lectureScore: "", // 평가 점수
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // if (!user) {
    //   alert("로그인 후 등록할 수 있습니다."); // 로그인 여부 확인
    //   return;
    // }

    setFormData({ ...formData });

    axios
      .post("/evaluation/write", formData)
      .then((response) => {
        console.log(response.data);
        alert("등록되었습니다");
        console.log(formData.evaluationID);
        navigate(`/${formData.evaluationID}`);
      })
      .catch((error) => {
        console.error(error);
        alert("등록에 실패했습니다");
      });
  };

  return (
    <Wrapper onSubmit={handleSubmit}>
      <TextAreaWrapper>
        <Label>강의명</Label>
        <Input
          type="text"
          name="lectureName"
          value={formData.lectureName}
          onChange={handleChange}
          placeholder="강의명을 입력하세요"
        />
      </TextAreaWrapper>

      <TextAreaWrapper>
        <Label>교수</Label>
        <Input
          type="text"
          name="professorName"
          value={formData.professorName}
          onChange={handleChange}
          placeholder="교수명을 입력하세요"
        />
      </TextAreaWrapper>

      <div>
        <Label>강의 수강 연도</Label>
        <Select
          name="lectureYear"
          value={formData.lectureYear}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="2020">2020</Option>
          <Option value="2021">2021</Option>
          <Option value="2022">2022</Option>
          <Option value="2023">2023</Option>
        </Select>
      </div>

      <div>
        <Label>학기 구분</Label>
        <Select
          name="semesterDivide"
          value={formData.semesterDivide}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="1학기">1학기</Option>
          <Option value="여름 계절학기">여름 계절학기</Option>
          <Option value="2학기">2학기</Option>
          <Option value="겨울 계절학기">겨울 계절학기</Option>
        </Select>
      </div>

      <div>
        <Label>강의 구분</Label>
        <Select
          name="lectureDivide"
          value={formData.lectureDivide}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="전공">전공</Option>
          <Option value="교양">교양</Option>
          <Option value="기타">기타</Option>
        </Select>
      </div>

      <TextAreaWrapper>
        <Label>평가 제목</Label>
        <Input
          type="text"
          name="evaluationTitle"
          value={formData.evaluationTitle}
          onChange={handleChange}
          placeholder="제목을 입력하세요"
        />
      </TextAreaWrapper>

      <TextAreaWrapper>
        <Label>평가 내용</Label>
        <TextArea
          name="evaluationContent"
          value={formData.evaluationContent}
          onChange={handleChange}
        ></TextArea>
      </TextAreaWrapper>

      <div>
        <Label>총점</Label>
        <Select
          name="totalScore"
          value={formData.totalScore}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="A+">A+</Option>
          <Option value="A">A</Option>
          <Option value="B+">B+</Option>
          <Option value="B">B</Option>
          <Option value="C+">C+</Option>
          <Option value="C">C</Option>
          <Option value="D+">D+</Option>
          <Option value="D">D</Option>
          <Option value="F">F</Option>
        </Select>
      </div>

      <div>
        <Label>학점</Label>
        <Select
          name="creditScore"
          value={formData.creditScore}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="A+">A+</Option>
          <Option value="A">A</Option>
          <Option value="B+">B+</Option>
          <Option value="B">B</Option>
          <Option value="C+">C+</Option>
          <Option value="C">C</Option>
          <Option value="D+">D+</Option>
          <Option value="D">D</Option>
          <Option value="F">F</Option>
        </Select>
      </div>

      <div>
        <Label>편의성</Label>
        <Select
          name="comfortableScore"
          value={formData.comfortableScore}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="A+">A+</Option>
          <Option value="A">A</Option>
          <Option value="B+">B+</Option>
          <Option value="B">B</Option>
          <Option value="C+">C+</Option>
          <Option value="C">C</Option>
          <Option value="D+">D+</Option>
          <Option value="D">D</Option>
          <Option value="F">F</Option>
        </Select>
      </div>

      <div>
        <Label>강의 평가 점수</Label>
        <Select
          name="lectureScore"
          value={formData.lectureScore}
          onChange={handleChange}
        >
          <Option value="">-- 선택 --</Option>
          <Option value="A+">A+</Option>
          <Option value="A">A</Option>
          <Option value="B+">B+</Option>
          <Option value="B">B</Option>
          <Option value="C+">C+</Option>
          <Option value="C">C</Option>
          <Option value="D+">D+</Option>
          <Option value="D">D</Option>
          <Option value="F">F</Option>
        </Select>
      </div>

      <ButtonWrapper>
        <Button type="submit">등록</Button>
      </ButtonWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.form`
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

const Label = styled.label`
  margin-bottom: 10px;
  font-size: 14px;
  margin-right: 10px;
`;

const Input = styled.input`
  width: 300px;
  padding: 10px;
  margin-bottom: 20px;
`;

const Select = styled.select`
  width: 300px;
  padding: 10px;
  margin-bottom: 20px;
`;

const Option = styled.Option`
  width: 100%;
  &:hover {
    background-color: #92b0fc;
  }
`;

const TextArea = styled.textarea`
  width: 300px;
  height: 100px;
  padding: 10px;
  margin-bottom: 20px;
`;

const TextAreaWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 20px;
`;

const Button = styled.button`
  padding: 10px 20px;
  background-color: #011445;
  color: white;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #02298d;
    transform: scale(1.05);
  }
`;

export default WriteBox;
