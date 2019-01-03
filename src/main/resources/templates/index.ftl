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
        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/oldie.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
        <script src="https://img.hcharts.cn/highcharts/themes/sand-signika.js"></script>
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
                                <h2>请检查Redmine是否可用。</h2>
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
                    <div class="col-lg-5 about-right">
                        <div id="container1" style="height: 400px; margin: 0 auto"></div>
                    </div>
                    <div class="offset-lg-1 col-lg-6">
                        <div id="container2" style="height: 400px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End About Area -->

        <!-- ####################### Start Scroll to Top Area ####################### -->
        <div id="back-top">
            <a title="Go to Top" href="#"></a>
        </div>
        <!-- ####################### End Scroll to Top Area ####################### -->

        <script type="text/javascript">
            var chart = null;

            $.getJSON('/redmine/getRedmineIssueJson', function (data) {
                chart = Highcharts.chart('container1',{
                    title: {
                        text: '百达金融（任务占比）'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                },
                                connectorColor: 'silver'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '任务完成占比',
                        data: [
                            ['郝亚强',   45.0],
                            ['郭佩鹏',       26.8],
                            ['王凯',       12.8],
                            ['耿宇航',    8.5],
                            ['桑小龙',     6.2],
                            ['其他',   0.7]
                        ]
                    }]
                });
            });

            $.getJSON('/redmine/getRedmineIssueJson', function (data) {
                chart = Highcharts.chart('container2', {
                    chart: {
                        zoomType: 'x'
                    },
                    title: {
                        text: '燃尽图'
                    },
                    subtitle: {
                        text: document.ontouchstart === undefined ?
                                '鼠标拖动可以进行缩放' : '手势操作进行缩放'
                    },
                    xAxis: {
                        type: 'datetime',
                        dateTimeLabelFormats: {
                            millisecond: '%H:%M:%S.%L',
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%m-%d',
                            week: '%m-%d',
                            month: '%Y-%m',
                            year: '%Y'
                        }
                    },
                    tooltip: {
                        xDateFormat: '%Y-%m-%d'
                    },
                    yAxis: {
                        title: {
                            text: '数量'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        area: {
                            fillColor: {
                                linearGradient: {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops: [
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                ]
                            },
                            marker: {
                                radius: 2
                            },
                            lineWidth: 1,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            threshold: null
                        }
                    },
                    series: [{
                        type: 'area',
                        name: '剩余任务',
                        data: data
                    }]
                });
            });
        </script>
    </body>
</@html>