# [level 5] IU와 콘의 보드게임 - 1841 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/1841) 

### 성능 요약

메모리: 91.7 MB, 시간: 413.03 ms

### 구분

코딩테스트 연습 > 2017 카카오코드 본선

### 채점결과

정확성: 100.0<br/>합계: 100.0 / 100.0

### 제출 일자

2026년 10월 05일 21:24:11

### 문제 설명

<h2>IU와 콘의 보드게임</h2>

<p><img src="https://t1.kakaocdn.net/codefestival/con1.png" title="" alt="img"></p>

<p>2017년 8월 5일, 다양한 방법으로 3단 고음을 마스터한 아이유는 신나게 놀기로 했다. 무엇을 할까 고민하던 그녀는 콘이 보드게임을 플레이하고 있는 것을 보고 흥미가 생겼다. 보드판은 다음과 같이 생겼다.</p>

<p><img src="https://t1.kakaocdn.net/codefestival/con2.png" title="" alt="img"></p>

<p>게임판에는 점 <code>A</code>, <code>B</code>, <code>C</code> 위치에 핀이 꼽혀 있으며 이 세 점은 게임판에서 가장 큰 삼각형을 이루고 있다. 고무줄이 세 개가 있는데 각각이 <code>A</code>와 <code>B</code>, <code>B</code>와 <code>C</code>, <code>C</code>와 <code>A</code>에 묶여 있어 삼각형 <code>ABC</code>를 그리고 있다. 이 커다란 삼각형 <code>ABC</code> 안에는 <code>n</code>개의 핀이 무작위로 꼽혀 있다.<br>
이 게임은 세 개의 고무줄을 삼각형 <code>ABC</code>의 안쪽으로 이동시켜 새로운 도형을 만들어 내는 것이 목표이며, 그 과정에서 핀의 위치는 고정되어 움직일 수 없다. 삼각형 <code>ABC</code> 내부의 핀들과 고무줄의 물리적 특성에 의해 이러한 방식으로 만들어지는 도형들은 다음과 같은 성질을 갖는다.</p>

<ul>
<li>꼭짓점은 모두 어떤 핀에 의해 만들어지며 변은 핀과 핀 사이의 선분이 된다.</li>
<li>끝점이 <code>A</code>와 <code>B</code>에 고정된 고무줄은 <code>A</code>에서 시작하여 <code>B</code>로 끝나는 안쪽으로 오목한 모양을 그리게 된다.</li>
<li>끝점이 <code>B</code>와 <code>C</code>에 고정된 고무줄은 <code>B</code>에서 시작하여 <code>C</code>로 끝나는 안쪽으로 오목한 모양을 그리게 된다.</li>
<li>끝점이 <code>C</code>와 <code>A</code>에 고정된 고무줄은 <code>C</code>에서 시작하여 <code>A</code>로 끝나는 안쪽으로 오목한 모양을 그리게 된다.</li>
</ul>

<p>여기에 아래의 추가 조건을 만족하는 도형을 모두 찾고 싶다.</p>

<ul>
<li>임의의 두 고무줄이 서로 교차하지 않으며, 점 <code>A</code>, <code>B</code>, <code>C</code>를 제외한 곳에서 겹치지 않는다. 따라서 만들어지는 도형의 내부와 외부가 명확하게 구분된다.</li>
<li>그 도형의 내부에 다른 핀이 존재하지 않는다.</li>
</ul>

<p><img src="https://t1.kakaocdn.net/codefestival/con3.png" title="" alt="img"></p>

<p>위의 첫 번째 그림은 게임판의 시작 형태를 보여주고 있다. 여기에는 삼각형 <code>ABC</code> 안에 8개의 핀이 꼽혀 있으며, 세 개의 고무줄은 서로 다른 색으로 표현되어 있다. 두 번째 그림은 추가 조건 두 가지를 모두 만족하는 도형의 예를 보여준다. 세 번째와 네 번째 그림은 고무줄로 만들 수 있는 모양이지만 추가 조건을 만족시키지 못하는 예이다. 세 번째 도형은 두 고무줄이 교차하여 추가 조건 1을 만족하지 못하고 네 번째 도형은 추가 조건 1을 만족하지만 내부에 핀이 존재하여 조건 2를 만족하지 못한다.<br>
위의 두 번째 도형과 같이 추가 조건 1과 2를 모두 만족하는 도형이 몇 개인지 맞히는 사람이 이기는 게임이다. 승부욕이 강한 아이유는 콘에게 지고 싶지 않아 여러분에게 도움을 요청했다. 삼각형 <code>ABC</code> 내부에 위치한 n개의 핀의 좌표가 주어질 때에 위의 조건을 만족하는 도형의 개수를 세는 프로그램을 작성하시오.</p>

<h3>입력 형식</h3>

<p>입력은 삼각형 내부의 핀의 개수 <code>n</code>, 삼각형의 좌표 <code>triangle</code>, 핀의 좌표 <code>v</code> 배열로 구성되어 있다. <code>triangle</code>은 <code>3 × 2</code> 크기의 2차원 배열이며, <code>v</code>는 <code>n × 2</code> 크기의 2차원 배열이다. 제한조건은 다음과 같다.</p>

<ul>
<li><code>0 &lt;= n &lt;= 500</code></li>
<li><code>triangle</code>과 <code>v</code>의 원소인 <code>x</code>좌표와 <code>y</code>좌표는 모두 <code>-100,000,000</code> 이상 <code>100,000,000</code> 이하이다.</li>
<li><code>triangle</code>과 <code>v</code>로 주어진 모든 점을 통틀어, 같은 좌표를 가진 두 핀이 입력으로 주어지지 않는다.</li>
<li><code>triangle</code>과 <code>v</code>로 주어진 모든 점을 통틀어, 임의의 세 점이 하나의 직선 위에 놓이는 경우는 없다.</li>
</ul>

<h3>출력 형식</h3>

<p>문제에 설명된 조건을 모두 만족하는 도형의 개수를 리턴한다.</p>

<h3>예제 입출력</h3>
<table class="table">
        <thead><tr>
<th>n</th>
<th>triangle</th>
<th>v</th>
<th>answer</th>
</tr>
</thead>
        <tbody><tr>
<td>2</td>
<td>[[0, 8], [-4, 0], [4, 0]]</td>
<td>[[-1, 3], [1, 4]]</td>
<td>6</td>
</tr>
<tr>
<td>10</td>
<td>[[-13, 40], [-27, -48], [31, 6]]</td>
<td>[[2, 21], [15, -1], [6, -13], [-20, -27], [5, 9], [5, 11], [-18, -26], [-15, 19], [7, 4], [-5, -18]]</td>
<td>73</td>
</tr>
</tbody>
      </table>
<h3>예제에 대한 설명</h3>

<p>첫 번째 예제에는 삼각형의 꼭짓점을 제외하고 총 2개의 핀의 위치가 주어졌다. 삼각형의 좌표를 <code>A(0, 8)</code>, <code>B(-4, 0)</code>, <code>C(4, 0)</code>, 그리고 삼각형 내부의 핀을 <code>D(-1, 3)</code>, <code>E(1, 4)</code>라고 하자. 고무줄로 만들 수 있으면서 추가 조건 1, 2를 만족하는 도형은 총 6개로 아래와 같다.<br>
사각형 <code>ABCD</code>, 사각형 <code>AEBC</code>, 오각형 <code>ADBCE</code>, 오각형 <code>ABDCE</code>, 오각형 <code>ADBEC</code>, 오각형 <code>ABDEC</code><br>
그 외의 도형은 모두 추가 조건 1 혹은 추가 조건 2를 만족하지 못한다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://school.programmers.co.kr/learn/challenges