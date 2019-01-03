<#include "indexDep.ftl">
<@html title="" lang="zh-CN">
    <!--头部 导航栏-->
    <header>
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
                    <img class="img-fluid" src="../img/about.jpg" alt="">
                </div>
                <div class="offset-lg-1 col-lg-6 offset-md-0 col-md-12 about-right">
                    <h1>
                        Over 2500 Courses <br> from 5 Platform
                    </h1>
                    <div class="wow fadeIn" data-wow-duration="1s">
                        <p>
                            There is a moment in the life of any aspiring astronomer that it is time to buy that first telescope. It’s exciting to think
                            about setting up your own viewing station. In the life of any aspiring astronomer that it is time to buy that first
                            telescope. It’s exciting to think about setting up your own viewing station.
                        </p>
                    </div>
                    <a href="courses.html" class="primary-btn">Explore Courses</a>
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
    </body>
</@html>