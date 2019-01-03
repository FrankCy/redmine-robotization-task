<#include "indexDep.ftl">
<@html title="" lang="zh-CN">
    <!--头部 导航栏-->
    <header>
        <script src="../js/vendor/jquery-2.2.4.min.js"></script>
        <script src="../js/vendor/bootstrap.min.js"></script>
        <script src="../js/easing.min.js"></script>
        <script src="../js/hoverIntent.js"></script>
        <script src="../js/superfish.min.js"></script>
        <script src="../js/jquery.ajaxchimp.min.js"></script>
        <script src="../js/jquery.magnific-popup.min.js"></script>
        <script src="../js/owl.carousel.min.js"></script>
        <script src="../js/owl-carousel-thumb.min.js"></script>
        <script src="../js/jquery.sticky.js"></script>
        <script src="../js/jquery.nice-select.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/waypoints.min.js"></script>
        <script src="../js/wow.min.js"></script>
        <script src="../js/jquery.counterup.min.js"></script>
        <script src="../js/mail-script.js"></script>
        <script src="../js/main.js"></script>
        <script src="http://code.highcharts.com/highcharts.js"></script>
    </header>
    <body>

        <!-- Start Banner Area -->
        <section class="home-banner-area relative">
            <div class="container">
                <div class="row fullscreen d-flex align-items-center justify-content-center">
                    <div class="banner-content col-lg-8 col-md-12">
                        <h1 class="wow fadeIn" data-wow-duration="4s">百达金融 <br> Redmine 任务管理平台</h1>
                        <p class="text-white">
                            请上传任务清单，系统会为您自动创建任务.
                        </p>

                        <div class="input-wrap">
                            <form method="POST" enctype="multipart/form-data" action="/redmine/upload" class="form-box d-flex justify-content-between">
                                <input type="file" placeholder="选择文件" class="custom-file" name="file">
                                <button type="submit" class="btn search-btn">上传</button>
                            </form>
                        </div>
                        <h4 class="text-white">团队成员</h4>

                        <div class="courses pt-20">
                            <#if !userIssueList?exists || userIssueList?size==0>
                            不可能走到这步
                            <#else>
                                <#list userIssueList as uiList>
                                <a href="#" data-wow-duration="1s" data-wow-delay="${uiList_index/10}s" class="primary-btn transparent mr-10 mb-10 wow fadeInDown">${uiList.userName} [<#if !uiList.userIssues?exists || uiList.userIssues?size==0>0<#else>${uiList.userIssues?size}</#if>]</a>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Banner Area -->

        <!-- Start About Area -->
        <section class="about-area section-gap">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-lg-5 col-md-6 about-left">
                        <#--<img class="img-fluid" src="../img/about.jpg" alt="">-->
                        <div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
                    </div>

                    <div class="offset-lg-1 col-lg-6 offset-md-0 col-md-12 about-right">
                        <h1>
                            BDJR-Redmine[Project] <br> 攀枝花存管
                        </h1>
                        <div class="wow fadeIn" data-wow-duration="1s">
                            <p>
                                项目描述信息巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉项目描述信息巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉项目描述信息巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉项目描述信息巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉项目描述信息巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End About Area -->


        <!-- Start Courses Area -->
        <section class="courses-area section-gap">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-5 about-right">
                        <h1>
                            This is Why <br> We have Solid Idea
                        </h1>
                        <div class="wow fadeIn" data-wow-duration="1s">
                            <p>
                                There is a moment in the life of any aspiring astronomer that it is time to buy that first telescope. It’s exciting to think
                                about setting up your own viewing station. In the life of any aspiring astronomer that it is time to buy that first
                                telescope. It’s exciting to think about setting up your own viewing station.
                            </p>
                        </div>
                        <a href="courses.html" class="primary-btn white">Explore Courses</a>
                    </div>
                    <div class="offset-lg-1 col-lg-6">
                        <div class="courses-right">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-12">
                                    <ul class="courses-list">
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay=".1s">
                                                <i class="fa fa-book"></i> Development
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay=".3s">
                                                <i class="fa fa-book"></i> IT & Software
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay=".5s">
                                                <i class="fa fa-book"></i> Photography
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay=".7s">
                                                <i class="fa fa-book"></i> Language
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay=".9s">
                                                <i class="fa fa-book"></i> Life Science
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay="1.1s">
                                                <i class="fa fa-book"></i> Business
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInLeft" href="courses.html" data-wow-duration="1s" data-wow-delay="1.3s">
                                                <i class="fa fa-book"></i> Socoal Science
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-12">
                                    <ul class="courses-list">
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay="1.3s">
                                                <i class="fa fa-book"></i> Data Science
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay="1.1s">
                                                <i class="fa fa-book"></i> Design
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay=".9s">
                                                <i class="fa fa-book"></i> Training
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay=".7s">
                                                <i class="fa fa-book"></i> Humanities
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay=".5s">
                                                <i class="fa fa-book"></i> Marketing
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay=".3s">
                                                <i class="fa fa-book"></i> Economics
                                            </a>
                                        </li>
                                        <li>
                                            <a class="wow fadeInRight" href="courses.html" data-wow-duration="1s" data-wow-delay=".1s">
                                                <i class="fa fa-book"></i> Personal Dev
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Courses Area -->

        <!-- ####################### Start Scroll to Top Area ####################### -->
        <div id="back-top">
            <a title="Go to Top" href="#"></a>
        </div>
        <!-- ####################### End Scroll to Top Area ####################### -->

        <script type="text/javascript">
            $(document).ready(function() {
                var title = {
                    text: '任务总览'
                };
                var subtitle = {
                    text: 'http://192.168.10.110:3000/'
                };
                var xAxis = {
                    categories: ['一月', '二月', '三月', '四月', '五月', '六月'
                        ,'七月', '八月', '九月', '十月', '十一月', '十二月']
                };
                var yAxis = {
                    title: {
                        text: '数量'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                };

                var tooltip = {
                    valueSuffix: '\xB0C'
                }

                var legend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                };

                var series =  [
                    {
                        name: '新建',
                        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,
                            26.5, 23.3, 18.3, 13.9, 9.6]
                    },
                    {
                        name: '开发中',
                        data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8,
                            24.1, 20.1, 14.1, 8.6, 2.5]
                    },
                    {
                        name: '已完成',
                        data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6,
                            17.9, 14.3, 9.0, 3.9, 1.0]
                    },
                    {
                        name: 'BUG',
                        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0,
                            16.6, 14.2, 10.3, 6.6, 4.8]
                    }
                ];

                var json = {};

                json.title = title;
                json.subtitle = subtitle;
                json.xAxis = xAxis;
                json.yAxis = yAxis;
                json.tooltip = tooltip;
                json.legend = legend;
                json.series = series;

                $('#container').highcharts(json);
            });
        </script>
    </body>
</@html>