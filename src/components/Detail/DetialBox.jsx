import { FaStar } from "react-icons/fa";
import { AiTwotoneLike } from "react-icons/ai";
import styled from "styled-components";

const DetailBox = () => {
  return (
    <div>
      <div>
        <div>영화로 보는 한국사</div>
        <div>장미애</div>
      </div>

      <div>
        <div>별점</div>
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="#fcc419" />
        <FaStar size="20" color="gray" />
      </div>

      <div>
        <div>강의 평가</div>
        <div>
          과제가 없어서 꿀 강의긴 한데 시험이 진짜 어렵습니다. 중간도 어렵고
          기말은 더 어려워요. 3시간 풀강하시고 가끔 넘어서까지
          수업하십니다...보여주시는 영화도 재밌고 수업도 열정적이시긴 합니다.
          한국사에 좀 관심 많고 수업만 제대로 들어도 성적 잘 나옵니다. (저도
          그랬어요) 시험 문제에 영화 장면 캡처해서 나오니까 주의하세요!
        </div>
      </div>

      <div>
        <AiTwotoneLike size="20" color="#d4d4d4" />
        <div>추천</div>
      </div>
    </div>
  );
};

export default DetailBox;
