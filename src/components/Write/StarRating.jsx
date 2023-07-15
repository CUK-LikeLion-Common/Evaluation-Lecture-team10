import React, { useState, useEffect } from "react";
import { FaStar } from "react-icons/fa";
import styled from "styled-components";

const ARRAY = [0, 1, 2, 3, 4];

const StarRating = () => {
  const [clicked, setClicked] = useState([false, false, false, false, false]);

  // 별 클릭하는 함수
  const handleStarClick = (index) => {
    let clickStates = [...clicked];
    for (let i = 0; i < 5; i++) {
      clickStates[i] = i <= index ? true : false;
    }
    setClicked(clickStates);
  };

  useEffect(() => {
    // 별점 click 하면 review를 등록한다
    sendReview();
  }, [clicked]);

  const sendReview = () => {
    let score = clicked.filter(Boolean).length;
  };

  return (
    <Wrapper>
      <Label>평가하기</Label>
      <Stars>
        {ARRAY.map((el, idx) => {
          return (
            <FaStar
              key={idx}
              size="20"
              onClick={() => handleStarClick(el)}
              className={clicked[el] && "yellowStar"}
            />
          );
        })}
      </Stars>
    </Wrapper>
  );
};

export default StarRating;

const Wrapper = styled.div`
  text-align: left;
`;

const Stars = styled.div`
  text-align: left;
  padding-top: 5px;

  & svg {
    color: gray;
    cursor: pointer;
  }

  :hover svg {
    color: #fcc419;
  }

  & svg:hover ~ svg {
    color: gray;
  }

  .yellowStar {
    color: #fcc419;
  }
`;

const Label = styled.div`
  text-align: left;
`;
