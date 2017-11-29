var substringMatcher = function (s) {
    return function findMatches(q, cb) {
        var matches;
        var url = 'Orders/AjaxGetJsonDataForTypeahead';

        // an array that will be populated with substring matches
        matches = [];

        $.ajax({

            type: 'GET',
            url: url,
            dataType: 'json',
            async: false,
            data: { type: s, search: q },
            success: function (data) {
                $.each(data, function (i, item) {
                    matches.push(item);
                });

                cb(matches);
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
            }
        });
    };
};

$('#compass').typeahead({
    hint: true,
    highlight: true,
    minLength: 0
},
{
    name: 'compass',
    source: substringMatcher("compass"),
    limit: 1000,
    minLength: 0
});

$('#route').typeahead({
    hint: true,
    highlight: true,
    minLength: 0
},
{
    name: 'route',
    source: substringMatcher("route"),
    limit: 1000,
    minLength: 0
});

$('#table').typeahead({
    hint: true,
    highlight: true,
    minLength: 0
},
{
    name: 'table',
    source: substringMatcher("table"),
    limit: 1000,
    minLength: 0
});

$('#auto').typeahead({
    hint: true,
    highlight: true,
    minLength: 0
},
{
    name: 'auto',
    source: substringMatcher("auto"),
    limit: 1000,
    minLength: 0
});
$("input.tt-hint").removeAttr("data-val data-val-required data-val-maxlength data-val-maxlength-max");
