import styled from "styled-components";
import img from "../img/main_evaluation_image.png";
import { FiSearch } from "react-icons/fi";
import LikeBox from "../components/Main/LikeBox";
import { useEffect, useState } from "react";
import axios from "axios";

const Wrapper = styled.div`
  text-align: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  padding: 30px;
  font-family: Inter;
  font-weight: 400;
`;

const Banner = styled.div`
  background-color: #0c2e86;
  height: 420px;
  margin: auto 0;
  min-width: 695px;
`;

const BannerBox = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-left: auto;
  margin-right: auto;
  grid-gap: 30px;
  width: 70vw;
  height: 420px;
  //margin: auto 0;
`;

const TextBox = styled.div`
  display: flex;
  flex-direction: row-reverse;
  margin: auto 0;
  margin-left: 150px;
`;

const Text = styled.span`
  color: #fff;
  font-size: 32px;
  text-align: right;
`;

const ImageBox = styled.div`
  text-align: left;
  margin: auto 0;
`;

const Image = styled.img.attrs({
  src: `${img}`,
})``;

const SearchBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 695px;
  height: 420px;
`;

const SearchTitle = styled.label`
  margin-top: 20px;
  text-align: left;
  font-size: 20px;
`;

const SearchInputBox = styled.div`
  display: block;
  margin-top: 30px;
  text-align: left;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-evenly;
  width: 695px;
  height: 30px;
`;

const SearchInput = styled.input`
  width: 650px;
  height: 20px;
  font-size: 15px;
  border: none;
  border-bottom: 1px solid;

  &:placeholder {
    color: #d9d9d9;
  }
`;

const SearchSvgInput = styled.input`
  display: none;
`;

const SearchSvg = styled(FiSearch)`
  width: 25px;
  height: 25px;
`;

const LikeTitle = styled.select`
  margin-top: 20px;
  text-align: left;
  font-size: 15px;
  float: left;
  margin-right: 10px;
`;

const LikeContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
`;

const Main = () => {
  const [evaluations, setEvaluation] = useState([]);
  const [page, setPage] = useState(1);
  const [search, setSearch] = useState(null);
  const [order, setOrder] = useState("최신순");
  const [divide, setDivide] = useState("전체");
  const onChangeOrder = (event) => setOrder(event.target.value);
  const onChangeDivide = (event) => setDivide(event.target.value);
  const onChangeSearch = (event) => setSearch(event.target.value);

  useEffect(() => {
    axios
      .get("/evaluation/read")
      .then((response) =>
        response.data
          ? setEvaluation(response.data.reverse().slice(0, 4))
          : setEvaluation(null)
      );
  }, []);
  const user_id = sessionStorage.getItem("user_id")
    ? sessionStorage.getItem("user_id")
    : null;
  const url = `evaluation/search/${page}?lectureDivide=${divide}&searchType=${order}&search=${search}`;

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!user_id) {
      alert("로그인 후 검색할 수 있습니다");
      return;
    }

    axios
      .get(url)
      .then((response) => {
        setEvaluation(response.data.result.evaluations);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const onInvalid = () => {
    alert("검색어를 입력하세요!");
  };

  return (
    <>
      <Wrapper>
        <Banner>
          <BannerBox>
            <TextBox>
              <Text>
                가톨릭대 강의 평가, 이제 CUKLION EDU에서 한 눈에 찾아보세요!
              </Text>
            </TextBox>
            <ImageBox>
              <Image />
            </ImageBox>
          </BannerBox>
        </Banner>
        <SearchBox>
          <SearchTitle htmlFor="submitBtn">강의 평가 검색하기</SearchTitle>
          <form onSubmit={handleSubmit}>
            <SearchInputBox>
              <SearchInput
                type="text"
                id="submitBtn"
                placeholder="강의명, 교수님 이름으로 검색할 수 있어요"
                onChange={onChangeSearch}
                onInvalid={onInvalid}
                required
              ></SearchInput>
              <label htmlfor="submitBtn">
                <SearchSvgInput type="submit" />
                <SearchSvg />
              </label>
            </SearchInputBox>
            <LikeTitle
              onChange={(event) => onChangeOrder(event)}
              name="searchType"
            >
              <option value="최신순">최신순</option>
              <option value="추천순">추천 많은 순</option>
            </LikeTitle>
            <LikeTitle
              onChange={(event) => onChangeDivide(event)}
              name="lectureDivide"
            >
              <option value="전체">전체</option>
              <option value="전공">전공</option>
              <option value="교양">교양</option>
              <option value="기타">기타</option>
            </LikeTitle>
          </form>
          <LikeContainer>
            {evaluations
              ? evaluations.map((evaluation) => (
                  <LikeBox
                    key={evaluation.evaluationID}
                    evaluationID={evaluation.evaluationID}
                    lectureName={evaluation.lectureName}
                    professorName={evaluation.professorName}
                    evaluationTitle={evaluation.evaluationTitle}
                    totalScore={evaluation.totalScore}
                    userID={evaluation.userID}
                  />
                ))
              : ""}
          </LikeContainer>
        </SearchBox>
      </Wrapper>
    </>
  );
};

export default Main;
