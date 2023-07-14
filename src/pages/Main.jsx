import styled from "styled-components";
import img from "../img/main_evaluation_image.png";
import { FiSearch } from "react-icons/fi";
import LikeBox from "../components/Main/LikeBox";
import { useState } from "react";

const ARRAY = [0, 1, 2, 3];

const lectures = ARRAY.map((array) => <LikeBox />);

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
`;

const LikeContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
`;

const Main = () => {
  const [selected, setSelected] = useState("popular");
  const onChange = (event) => setSelected(event.target.value);
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
          <form>
            <SearchInputBox>
              <SearchInput
                type="text"
                id="submitBtn"
                placeholder="강의명, 교수님 이름으로 검색할 수 있어요"
              ></SearchInput>
              <label htmlfor="submitBtn">
                <SearchSvgInput type="submit" />
                <SearchSvg />
              </label>
            </SearchInputBox>
            <LikeTitle onChange={(event) => onChange(event)} name="order">
              <option value="popular">추천 많은 순</option>
              <option value="latest">최신순</option>
            </LikeTitle>
          </form>
          <LikeContainer>{lectures}</LikeContainer>
        </SearchBox>
      </Wrapper>
    </>
  );
};

export default Main;
