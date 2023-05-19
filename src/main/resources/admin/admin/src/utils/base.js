const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot6ygmo/",
            name: "springboot6ygmo",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot6ygmo/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于vue框架的动漫视频网站"
        } 
    }
}
export default base
