import styled from "styled-components";
import img from "../img/main_evaluation_image.png";
import { FiSearch } from "react-icons/fi";
import LikeBox from "../components/Main/LikeBox";

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
  width: 1440px;
  height: 420px;
`;

const BannerBox = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-left: auto;
  margin-right: auto;
  grid-gap: 30px;
  width: 695px;
  height: 420px;
`;

const Text = styled.div`
  margin: auto 0;
  text-align: left;
  color: #fff;
  font-size: 32px;
`;

const Image = styled.img.attrs({
  src: `${img}`,
})`
  margin: auto 0;
`;

const SearchBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 695px;
  height: 420px;
`;

const SearchTitle = styled.div`
  margin-top: 20px;
  text-align: left;
  font-size: 20px;
`;

const SearchInputBox = styled.div`
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

const SearchSvg = styled(FiSearch)`
  width: 25px;
  height: 25px;
`;

const LikeTitle = styled.div`
  margin-top: 20px;
  text-align: left;
  font-size: 15px;
`;

const LikeContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
`;

const Main = () => {
  return (
    <>
      <Wrapper>
        <Banner>
          <BannerBox>
            <Text>
              가톨릭대 강의 평가, &nbsp; 이제 CUKLION EDU에서 한 눈에
              찾아보세요!
            </Text>
            <Image />
          </BannerBox>
        </Banner>
        <SearchBox>
          <SearchTitle>강의 평가 검색하기</SearchTitle>
          <SearchInputBox>
            <SearchInput
              type="text"
              placeholder="강의명, 교수님 이름으로 검색할 수 있어요"
            ></SearchInput>
            <SearchSvg />
          </SearchInputBox>
          <LikeTitle>추천 많은 순</LikeTitle>
          <LikeContainer>{lectures}</LikeContainer>
        </SearchBox>
      </Wrapper>
    </>
  );
};

export default Main;
