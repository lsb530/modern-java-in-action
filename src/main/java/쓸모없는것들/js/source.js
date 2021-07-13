document.addEventListener("DOMContentLoaded", function(){
    const dataDiv = document.getElementById("data");
    const showBtn = document.getElementById("showBtn");
    const flipBtn = document.getElementById("flipBtn");
    const shiftBtn = document.getElementById("shiftBtn");
    const startBtn = document.getElementById("startBtn");
    const cards = init();
    showBtn.addEventListener('click', (e) => showCards());
    shiftBtn.addEventListener('click', (e) => shiftCards(dataDiv, cards));
    flipBtn.addEventListener('click', (e) => flipAll());
    startBtn.addEventListener('click', (e) => startGame(dataDiv, cards));
});

// 전역 변수
let playing = false; // 뒤집기상태(게임중)인지 체크
let gamePack = []; // e.target.id가 담길 배열
let srcArr = []; // e가 담길 배열
let score = 0;

// 최초렌더링 함수 + 카드 이미지 이벤스리스너 추가
function init() {
    playing = false;
    const dataDiv = document.getElementById("data");
    const cards = createCards();
    for(let i=0, len=cards.length; i<len; i++) {
        dataDiv.innerHTML += `<img class='s' id='${cards[i]}' src='images/${cards[i]}.png'>`;
    }
    addEventListener('click', (e) => { if(e.target.tagName === 'IMG') clickCard(e); });
    // gameTimeShow();
    // leftCardCount();
    showScore();
    return cards;
}

// 시작
function startGame(dataDiv, cards) {
    gameTimeShow();
    flipAll();
    shiftCards(dataDiv, cards);
}

// 카드 생성
function createCards() {
    const cards = [];
    for(let i=0; i<52; i++) { cards.push(i); }
    return cards;
}

// 카드 섞기
function shiftCards(target, cards) {
    const shuffledCards = [...cards]; // deepCopy
    shuffledCards.sort(() => Math.random()-0.5);
    target.innerHTML = '';
    for(let i=0, len=shuffledCards.length; i<len; i++) {
        target.innerHTML += `<img class='h' id='${ shuffledCards[i] }' src='images/reverse.png'>`;
    }
    return shuffledCards;
}

// 카드 모두 뒤집기
function flipAll() {
    playing = true;
    const images = document.querySelectorAll('img');
    for(let i=0, len=images.length; i<len; i++) {
        const clName = images[i].className;
        if (clName !== 'answer') {
            images[i].classList.remove('s');
            images[i].className = 'h';
            images[i].src = `images/reverse.png`;
        }
    }
}

// 카드 보여주기
function showCards() {
    playing = false;
    const images = document.querySelectorAll('img');
    for(let i=0, len=images.length; i<len; i++) {
        const clName = images[i].className;
        if (clName !== 'answer') {
            images[i].classList.remove('h');
            images[i].className = 's';
            images[i].src = `images/${images[i].id}.png`;
        }
    }
}

// 게임함수
function clickCard(e) {
    if (!playing) return;
    if (!isRear(e)) return;
    classChanger(e, 's', 'h', e.target.id);
    if (isFirstCard(gamePack)) { // 첫번째 카드이면
        gamePack.push(e.target.id);
        srcArr.push(e);
    } else { // 두번째 카드이면
        gamePack.push(e.target.id);
        srcArr.push(e);
        if(compare(gamePack[0], gamePack[1])) { // 정답이면
            for(let i=0, len=srcArr.length; i<len; i++) {
                classChanger(srcArr[i], 'answer', 'h', gamePack[i]);
            }
            score += 10;
            showScore(score);
        } else { // 오답이면
            doubleFlipCards(srcArr);
        }
        gamePack = [];
        srcArr = [];
    }
    // leftCardCount();
}

// 둘다 뒤집기
function doubleFlipCards(list) {
    setTimeout(() => {
        for(let i=0, len=list.length; i<len; i++) {
            classChanger(list[i], 'h', 's', 'reverse');
        }
    }, 500);
}

// 카드 앞면이면 true, 뒷면이면 false
function isRear(e) {
    return e.target.className !== 's' && e.target.className !== 'answer';
}

// 첫번째 카드인지 확인
function isFirstCard(card) {
    return card.length !== 1;
}

// 두번째 카드일때 compare
function compare(one, two) { // 1이면 답, 0이면 오답으로 처리하자!!
    console.log('답 체크중....');
    console.log('계산값',Math.abs((two-one)/13));
    return (Math.abs((two-one)/13)) === 2;
}

// 클래스 추가 또는 삭제, 경로 변경 메서드
function classChanger(e, cre, del, src) {
    e.target.classList.remove(del);
    e.target.className = cre;
    e.target.src = `images/${src}.png`;
}

// 남은 카드 카운팅해서 보여주는 함수
// function leftCardCount() {
//     const leftCards = document.getElementById("leftCardDiv");
//     const images = document.querySelectorAll('img');
//     let count = 0;
//     for(let i=0, len=images.length; i<len; i++) {
//         const clName = images[i].className;
//         if (clName !== 'answer') {
//             count ++;
//         }
//     }
//     leftCards.innerHTML = `<h3>남은 카드는 ${count} 개입니다</h3>`;
// }

// 게임 시간 보여주는 함수
function gameTimeShow() {
    let time = 300; // 기준시간
    let min = ''; //분
    let sec = ''; //초
    let x = setInterval(() => {
        min = parseInt(time/60); // 몫을 계산
        sec = time%60; // 나머지를 계산
        document.getElementById('leftTimeDiv').innerHTML = `${min}분 ${sec}초 남았습니다`;
        time--;
        if (time <= 0) {
            clearInterval(x);
            document.getElementById('leftTimeDiv').innerHTML = `시간초과!!!!`;
            alert('시간초과하셨습니다^^');
        }
    }, 1000);
}

// 점수 보여주는 함수
function showScore() {
    const zeroFilled = ('000' + score).substr(-3);
    const scoreBoard = document.getElementById("scoreDiv");
    scoreBoard.innerHTML = zeroFilled;
}