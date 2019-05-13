/**
 * Created by sr on 2019/02/26.
 */


//    
//应急事件处置
// $(".message_scroll").click(function () {
//     EventClick();
//     eventHandle();
// });



// 左侧部分 - 底部
    function Echarts() {
        var myChart = echarts.init($("#container_huan_l")[0]);
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:['关机','待机','运行','故障','维修'],
                textStyle:{
                    color:"#e9ebee"
                }
            },
            series: [
                {
                    name:'当前数据',
                    type:'pie',
                    center:['80%','50%'],
                    radius: ['50%', '80%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    },
                    data:[
                        {value:335, name:'关机'},
                        {value:310, name:'待机'},
                        {value:234, name:'运行'},
                        {value:135, name:'故障'},
                        {value:1548, name:'维修'}
                    ]
                }
            ]
        };
        myChart.setOption(option);
    }
// 中间部分 - 中部01
    // $(function(){
    //     var myChart = echarts.init($("#mid01")[0]);
    //     option = {
    //         title : {
    //             text: '销售量组成',
    //             textStyle:{
    //                 color:"#e9ebee"

    //             },

    //             x:'center'
    //         },
    //         tooltip : {
    //             trigger: 'item',
    //             formatter: "{a} <br/>{b} : {c} ({d}%)"
    //         },
    //         legend: {
    //             orient : 'vertical',
    //             x : 'left',
    //             data:['销售A','销售B','销售C','销售D','销售E'],
    //             textStyle:{
    //                 color:"#e9ebee"

    //             }
    //         },

    //         calculable : false,
    //         series : [
    //             {
    //                 name:'销售组成',
    //                 type:'pie',
    //                 radius : '55%',
    //                 center: ['50%', '60%'],
    //                 data:[
    //                     {value:335, name:'销售A',
    //                         itemStyle:{
    //                             normal:{
    //                                 color:'#1afffd'

    //                             }
    //                         }
    //                     },
    //                     {value:310, name:'销售B',
    //                         itemStyle:{
    //                             normal:{
    //                                 color:'#2e7cff'

    //                             }
    //                         }},
    //                     {value:234, name:'销售C',
    //                         itemStyle:{
    //                             normal:{
    //                                 color:'#ffcb89'

    //                             }
    //                         }},
    //                     {value:135, name:'销售D',
    //                         itemStyle:{
    //                             normal:{
    //                                 color:'#005ea1'

    //                             }
    //                         }},
    //                     {value:1548, name:'销售E',
    //                         itemStyle:{
    //                             normal:{
    //                                 color:'#0ad5ff'

    //                             }
    //                         }}
    //                 ]
    //             }
    //         ]
    //     };


    //     myChart.setOption(option);
    // });

// 中间部分 - 中部02
    $(function(){
        $.ajax({
            type: "GET",
            url: "http://10.166.0.7:8080/getEquipSort",
            data: '',
            dataType: "json",
            success: function(data){
                console.log(data);

            }
        });




    });

// 中间部分 - 底部
    $(function(){

    });


// 右侧部分 - 底部
//     $(function(){
//         var myChart = echarts.init($("#container_huan_r")[0]);
//         option = {
//             tooltip : {
//                 trigger: 'axis'
//             },
//             grid: {
//                 x: 46,
//                 y:30,
//                 x2:30,
//                 y2:20,
//                 borderWidth: 0
//             },
//
//             calculable : false,
//             legend: {
//                 data:['开机','关机','故障','维修'],
//                 textStyle:{
//                     color:"#e9ebee"
//
//                 }
//             },
//             xAxis : [
//                 {
//                     type : 'category',
//                     splitLine : {show : false},
//                     data : ['周一','周二','周三','周四','周五','周六','周日'],
//                     axisLabel: {
//                         show: true,
//                         textStyle: {
//                             color: '#fff',
//                             align: 'center'
//                         }
//                     }
//                 }
//             ],
//             yAxis : [
//                 {
//                     type : 'value',
//                     position: 'right',
//                     splitLine : {show : false},
//                     axisLabel: {
//                         show: true,
//                         textStyle: {
//                             color: '#fff',
//                             align: 'center'
//                         }
//                     }
//                 }
//             ],
//             series : [
//                 {
//                     name:'开机',
//                     type:'bar',
//                     data:[320, 332, 301, 334, 390, 330, 320],
//                     itemStyle: {
//                         normal: {
//                             color:"#2e7cff"
//                         }
//                     }
//                 },
//                 {
//                     name:'关机',
//                     type:'bar',
//                     tooltip : {trigger: 'item'},
//                     stack: '广告',
//                     data:[120, 132, 101, 134, 90, 230, 210],
//                     itemStyle: {
//                         normal: {
//                             color:"#feb602"
//                         }
//                     }
//                 },
//                 {
//                     name:'故障',
//                     type:'bar',
//                     tooltip : {trigger: 'item'},
//                     stack: '广告',
//                     data:[220, 182, 191, 234, 290, 330, 310],
//                     itemStyle: {
//                         normal: {
//                             color:"#ffcb89"
//                         }
//                     }
//                 },
//                 {
//                     name:'维修',
//                     type:'bar',
//                     tooltip : {trigger: 'item'},
//                     stack: '广告',
//                     data:[150, 232, 201, 154, 190, 330, 410],
//                     itemStyle: {
//                         normal: {
//                             color:"#005ea1"
//                         }
//                     }
//                 },
//
//                 {
//                     name:'订单趋势',
//                     type:'line',
//                     data:[862, 1018, 964, 1026, 1679, 1600, 1570],
//                     itemStyle: {
//                         normal: {
//                             color:"#0ad5ff"
//                         }
//                     }
//                 },
//
//                 {
//                     name:'开机细分',
//                     type:'pie',
//                     tooltip : {
//                         trigger: 'item',
//                         formatter: '{a} <br/>{b} : {c} ({d}%)'
//                     },
//                     center: [100,80],
//                     radius : [0, 30],
//                     itemStyle :　{
//                         normal : {
//                             labelLine : {
//                                 length : 20
//                             }
//                         }
//                     },
//                     data:[
//                         {value:1048, name:'运行',
//                             itemStyle: {
//                                 normal: {
//                                     color:"#1afffd"
//                                 }
//                             }},
//                         {value:251, name:'待机',
//                             itemStyle: {
//                                 normal: {
//                                     color:"#2e7cff"
//                                 }
//                             }},
//                         {value:147, name:'报警',
//                             itemStyle: {
//                                 normal: {
//                                     color:"#ffcb89"
//                                 }
//                             }}
//                     ]
//                 }
//             ]
//         };
//
//
//         myChart.setOption(option);
//     });




