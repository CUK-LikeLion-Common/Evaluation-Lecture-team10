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
      <div>
        <label>강의명</label>
        <input
          type="text"
          name="lectureName"
          value={formData.lectureName}
          onChange={handleChange}
          placeholder="강의명을 입력하세요"
        />
      </div>

      <div>
        <label>교수</label>
        <input
          type="text"
          name="professorName"
          value={formData.professorName}
          onChange={handleChange}
          placeholder="교수명을 입력하세요"
        />
      </div>

      <div>
        <label>강의 수강 연도</label>
        <select
          name="lectureYear"
          value={formData.lectureYear}
          onChange={handleChange}
        >
          <option value="">선택</option>
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
          <option value="2023">2023</option>
        </select>
      </div>

      <div>
        <label>학기 구분</label>
        <select
          name="semesterDivide"
          value={formData.semesterDivide}
          onChange={handleChange}
        >
          <option value="">선택</option>
          <option value="1학기">1학기</option>
          <option value="여름 계절학기">여름 계절학기</option>
          <option value="2학기">2학기</option>
          <option value="겨울 계절학기">겨울 계절학기</option>
        </select>
      </div>

      <div>
        <label>강의 구분</label>
        <select
          name="lectureDivide"
          value={formData.lectureDivide}
          onChange={handleChange}
        >
          <option value="">선택</option>
          <option value="전공">전공</option>
          <option value="교양">교양</option>
          <option value="기타">기타</option>
        </select>
      </div>

      <div>
        <label>평가 제목</label>
        <input
          type="text"
          name="evaluationTitle"
          value={formData.evaluationTitle}
          onChange={handleChange}
          placeholder="제목을 입력하세요"
        />
      </div>

      <div>
        <label>평가 내용</label>
        <textarea
          name="evaluationContent"
          value={formData.evaluationContent}
          onChange={handleChange}
        ></textarea>
      </div>

      <div>
        <label>총점</label>
        <select
          name="totalScore"
          value={formData.totalScore}
          onChange={handleChange}
        >
          <option value="">-- 선택 --</option>
          <option value="A+">A+</option>
          <option value="A">A</option>
          <option value="B+">B+</option>
          <option value="B">B</option>
          <option value="C+">C+</option>
          <option value="C">C</option>
          <option value="D+">D+</option>
          <option value="D">D</option>
          <option value="F">F</option>
        </select>
      </div>

      <div>
        <label>학점</label>
        <select
          name="creditScore"
          value={formData.creditScore}
          onChange={handleChange}
        >
          <option value="">-- 선택 --</option>
          <option value="A+">A+</option>
          <option value="A">A</option>
          <option value="B+">B+</option>
          <option value="B">B</option>
          <option value="C+">C+</option>
          <option value="C">C</option>
          <option value="D+">D+</option>
          <option value="D">D</option>
          <option value="F">F</option>
        </select>
      </div>

      <div>
        <label>편의성 (강의가 널널한가?)</label>
        <select
          name="comfortableScore"
          value={formData.comfortableScore}
          onChange={handleChange}
        >
          <option value="">-- 선택 --</option>
          <option value="A+">A+</option>
          <option value="A">A</option>
          <option value="B+">B+</option>
          <option value="B">B</option>
          <option value="C+">C+</option>
          <option value="C">C</option>
          <option value="D+">D+</option>
          <option value="D">D</option>
          <option value="F">F</option>
        </select>
      </div>

      <div>
        <label>강의 평가 점수</label>
        <select
          name="lectureScore"
          value={formData.lectureScore}
          onChange={handleChange}
        >
          <option value="">-- 선택 --</option>
          <option value="A+">A+</option>
          <option value="A">A</option>
          <option value="B+">B+</option>
          <option value="B">B</option>
          <option value="C+">C+</option>
          <option value="C">C</option>
          <option value="D+">D+</option>
          <option value="D">D</option>
          <option value="F">F</option>
        </select>
      </div>

      <button type="submit">등록</button>
    </Wrapper>
  );
};

const Wrapper = styled.form`
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

export default WriteBox;
