<template>
  <div class="search_container">
    <h1 class="heading">여행지를 검색합니다</h1>
    <div class="searchInputWrapper">
      <input
        class="searchInput"
        type="text"
        placeholder="여행지를 검색해주세요" />
      <!-- <i class="searchInputIcon fa fa-search"></i> -->
      <font-awesome-icon :icon="['fas', 'search']" class="searchInputIcon"/>
    </div>
    <div id="select_container">
      <div>검색</div>
      <div>추천</div>
      <div>거리순</div>
      <div>MY</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
/* Quick and dirty normalize hax */
#select_container {
  display: flex;
  align-items: center;
  justify-content:space-between;
  text-align: center;
  width: 93%;
}

#select_container > div {
  padding: 10px 20px;
  margin: 10px;
  border-radius: 10px;
  cursor: pointer;
}

#select_container > div:hover {
  background-color: #017e94;
}

.search_container {
  height: 0%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #FFF;
  background-color: #0097b2;
  border-radius: 10px 10px 0px 0px;
}

.heading {
  margin-bottom: 1.5rem;
  font-size: 1.2rem;
}

.searchInputWrapper {
  position: relative;
}

.searchInput {
  width: 18rem;
  height: 2rem;
  padding: 0.2rem 3rem 0 1rem;
  border-radius: 0.4rem;
  border: none;
  transition: transform 0.1s ease-in-out;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  font-family: cookierunotf;
}

::placeholder {
  color: #a1a1a1;
}

/* hide the placeholder text on focus */
:focus::placeholder {
  text-indent: -999px;
}

.searchInput:focus {
  outline: none;
  transform: scale(1.1);
  transition: all 0.1s ease-in-out;
}

.searchInputIcon {
  position: absolute;
  right: 0.5rem;
  top: 0.7rem;
  color: #a1a1a1;
  transition: all 0.1s ease-in-out;
}

.container:focus-within > .searchInputWrapper > .searchInputIcon {
  right: 0.2rem;
}
</style>
