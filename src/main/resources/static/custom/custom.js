;var $table = $('#table'),
    $remove = $('#remove'),
    $add = $('#add'),
    $edit = $('#edit'),
    $save = $('#save'),
    selections = [];

function initTable() {
    $table.bootstrapTable({
        height: getHeight(),
        columns: [{
            field: 'state',
            checkbox: true,
            align: 'center'
        }, {
            title: 'ID',
            field: 'id',
            align: 'center',
            sortable: true,
            footerFormatter: totalNameFormatter
        }, {
            field: 'name',
            title: 'Name',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }, {
            field: 'mail',
            title: 'E-Mail',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }, {
            field: 'relevance',
            title: 'Relevance',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }, {
            field: 'job',
            title: 'Job Titile',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }, {
            field: 'companyName',
            title: 'Company',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }, {
            field: 'companyLink',
            title: 'Company link',
            sortable: true,
            editable: true,
            align: 'center',
            footerFormatter: totalTextFormatter
        }]
    });
    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });
    $remove.click(function () {
        var ids = getIdSelections();
        console.log(JSON.stringify(ids));
        var removeMail = function (id) {
            $.ajax({
                url: window.location.pathname + '/remove',
                type: 'POST',
                timeout: 10000,
                crossDomain: true,
                dataType: "json",
                data: JSON.stringify(ids),
                contentType: "application/json; charset=UTF-8",
                beforeSend: function () {
                    // TODO: should be some on before listener
                },
                error: function (request) {
                    console.log(request);
                },
                success: function (request) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: ids
                    });
                    $remove.prop('disabled', true);
                }
            });
        };

        removeMail(ids);
    });
    $add.click(function () {
        $('#modal').modal('show');
    });

    $save.click(function () {
        var mail = {};
        mail.name = $('#name').val();
        mail.surname = $('#surname').val();
        mail.mail = $('#e-mail').val();
        mail.relevance = $('#relevance').val();
        mail.job = $('#job').val();
        mail.companyName = $('#company-name').val();
        mail.companyLink = $('#company-link').val();

        console.log(JSON.stringify(mail));
        var addMail = function (mail) {
            $.ajax({
                url: window.location.pathname + '/add',
                type: 'POST',
                timeout: 10000,
                crossDomain: true,
                dataType: "json",
                data: JSON.stringify(mail),
                contentType: "application/json; charset=UTF-8",
                beforeSend: function () {
                    // TODO: should be some on before listener
                },
                error: function (request) {
                    console.log(request);
                },
                success: function (request) {

                    $table.bootstrapTable('insertRow', {
                        index: $table.bootstrapTable('getData').length + 1,
                        row: {
                            id: request.id,
                            name: mail.name + ' ' + mail.surname,
                            mail: mail.mail,
                            relevance: mail.relevance,
                            job: mail.job,
                            companyName: mail.companyName,
                            companyLink: mail.companyLink
                        }
                    });
                }
            });
        };

        addMail(mail);
    });

    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}

function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-heart"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};

function totalTextFormatter(data) {
    return 'Total';
}

function totalNameFormatter(data) {
    return data.length;
}

function totalPriceFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.price.substring(1));
    });
    return '$' + total;
}

function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}

$(function () {
    var scripts = [location.search.substring(1) || 'bootstrap-table/src/bootstrap-table.js',
            'bootstrap-table/src/extensions/export/bootstrap-table-export.js',
            'http://rawgit.com/hhurz/tableExport.jquery.plugin/master/tableExport.js',
            'bootstrap-table/src/extensions/editable/bootstrap-table-editable.js',
            'http://rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js'],
        eachSeries = function (arr, iterator, callback) {
            callback = callback || function () {
                };
            if (!arr.length) {
                return callback();
            }
            var completed = 0;
            var iterate = function () {
                iterator(arr[completed], function (err) {
                    if (err) {
                        callback(err);
                        callback = function () {
                        };
                    }
                    else {
                        completed += 1;
                        if (completed >= arr.length) {
                            callback(null);
                        }
                        else {
                            iterate();
                        }
                    }
                });
            };
            iterate();
        };

    eachSeries(scripts, getScript, initTable);
});

function getScript(url, callback) {
    var head = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.src = url;

    var done = false;
    // Attach handlers for all browsers
    script.onload = script.onreadystatechange = function () {
        if (!done && (!this.readyState ||
            this.readyState == 'loaded' || this.readyState == 'complete')) {
            done = true;
            if (callback)
                callback();

            // Handle memory leak in IE
            script.onload = script.onreadystatechange = null;
        }
    };

    head.appendChild(script);

    // We handle everything using the script element injection
    return undefined;
}