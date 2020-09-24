<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Upload Creature</title>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

</head>
<body>
<h3 th:text="#{home.welcome}">Default Title</h3>

<form th:action="@{/uploadcreature}" th:object="${creature}" method="post" id="monster-form"
      onsubmit="cleanupSubmitForm()">
    <table border="1">
        <tr>
            <td colspan="3"><label for="creatureName"/><input th:type="text" th:value="*{creatureName}"
                                                              id="creatureName" th:placeholder="'Creature Name'"/></td>

        </tr>
        <tr>
            <td><label for="size">Size: </label><select th:value="*{size}" id="size">
                <option th:each="s : ${T(org.nerdcore.dmdatatools.GameEntities.Creature.Size).values()}" th:value="${s}"
                        th:text="${#strings.capitalizeWords(#strings.toLowerCase(s))}"></option>
            </select>
            </td>
            <td><label for="creatureType">Creature Type: </label><select th:value="*{creatureType}" id="creatureType">
                <option th:each="t : ${T(org.nerdcore.dmdatatools.GameEntities.Creature.DefaultCreatureType).values()}"
                        th:value="${t}" th:text="${#strings.capitalizeWords(#strings.toLowerCase(t))}"></option>
            </select>
            </td>
            <td>
                <label for="alignment">Alignment: </label><select th:value="alignment" id="alignment">
                <option th:each="a : ${T(org.nerdcore.dmdatatools.GameEntities.Creature.Alignment).values()}"
                        th:value="${a}"
                        th:text="${#strings.capitalizeWords(#strings.toLowerCase(#strings.replace(a, '_', ' ')))}"></option>

            </select>
            </td>
        </tr>
        <tr>
            <td>
                <label th:for="hitDieCount">HitDie:</label>
                <input type="number" th:field="*{hitDieCount}" id="hitDieCount" name="hitDieCount" min="1" max="40"
                       value="1" th:onchange="writeHitDieDisplay()">
                <select th:field="*{hitDieBase}" id="hitDieBase" name="hitDieBase" th:onchange="writeHitDieDisplay()">
                    <option th:value="4" th:text="'d4'" selected></option>
                    <option th:value="6" th:text="'d6'"></option>
                    <option th:value="8" th:text="'d8'"></option>
                    <option th:value="10" th:text="'d10'"></option>
                    <option th:value="12" th:text="'d12'"></option>
                    <option th:value="20" th:text="'d20'"></option>
                </select>
                <span id="hpDisplay"> - </span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span><strong>Speed: </strong></span>
                <label th:for="ground">Ground: </label><input type="number" th:id="ground"
                                                              th:field="*{groundSpeed}" min="0" max="200"
                                                              step="5">
                <label th:for="burrow">Burrow: </label><input type="number" th:id="burrow"
                                                              th:field="*{burrowSpeed}" min="0" max="200"
                                                              step="5">
                <label th:for="climb">Climb: </label><input type="number" th:id="climb"
                                                            th:field="*{climbSpeed}" min="0" max="200"
                                                            step="5">
                <label th:for="fly">Fly: </label><input type="number" th:id="fly" th:field="*{flySpeed}"
                                                        min="0" max="200" step="5">
                <label th:for="swim">Swim: </label><input type="number" th:id="swim" th:field="*{swimSpeed}"
                                                          min="0" max="200" step="5">

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th></th>
                        <th>STR</th>
                        <th>DEX</th>
                        <th>CON</th>
                        <th>INT</th>
                        <th>WIS</th>
                        <th>CHA</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td th:each="score, iterStat : ${creature.abilityScores}">
                            <select th:field="*{abilityScores[__${iterStat.index}__]}"
                                    th:name="|abilityScores${iterStat.index}|" th:onchange="writeHitDieDisplay()"/>
                            <option th:each="i : ${#numbers.sequence(0,30)}" th:value="${i}" th:text="${i}"
                                    th:selected="${i==score}"/>
                            </select>
                            <div>(+0)</div>
                        </td>
                    </tr>
                    <tr>
                        <td th:text="'Saving Throws?'"></td>
                        <td th:each="entry : ${creature.savingThrows}">
                            <input type="checkbox" th:field="*{savingThrows[__${entry.key}__]}">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <table>
                    <tr>
                        <td>
                            <label th:for="damageResistancesChoices">Damage Resistances:</label>
                        </td>
                        <td>
                            <select id="damageResistancesChoices" size="3" th:field="*{damageResistances}" multiple>
                                <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label th:for="damageImmunityChoices">Damage Immunities:</label>
                        </td>
                        <td>
                            <select id="damageImmunityChoices" size="3" th:field="*{damageImmunities}"
                                    style="text-indent: hanging; width:150px; white-space: pre-wrap" multiple>
                                <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label th:for="damageVulnerabilityChoices">Damage Vulnerabilities:</label>
                        </td>
                        <td>
                            <select id="damageVulnerabilityChoices" size="3" th:field="*{damageVulnerabilities}"
                                    multiple>
                                <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label th:for="conditionImmunities">Condition Immunities:</label>
                        </td>
                        <td>
                            <select id="conditionImmunities" size="3" th:field="*{conditionImmunities}" multiple>
                                <option th:each="con : ${@conditionList}" th:text="${con}" th:value="${con}"></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>Other Senses: </span></td>
                        <td>
                            <label th:for="blindsight">Blindsight: </label><input type="number" th:id="blindsight"
                                                                                  th:field="*{blindsight}" min="0"
                                                                                  max="300" step="10">
                            <label th:for="darkvision">Darkvision: </label><input type="number" th:id="darkvision"
                                                                                  th:field="*{darkvision}" min="0"
                                                                                  max="300" step="10">
                            <label th:for="tremorsense">Tremorsense: </label><input type="number" th:id="tremorsense"
                                                                                    th:field="*{tremorsense}" min="0"
                                                                                    max="300" step="10">
                            <label th:for="truesight">Truesight: </label><input type="number" th:id="truesight"
                                                                                th:field="*{truesight}" min="0"
                                                                                max="300"
                                                                                step="10">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label th:for="skills">Skills:</label>
                        </td>
                        <td>
                            <select th:field="*{skills}" id="skills" size="3" multiple>
                                <option th:each="skill : ${@skillList}" th:text="${skill}" th:value="${skill}"></option>
                            </select>
                        </td>
                    </tr>
                    </td>
                </table>
        </tr>
        <tr>
            <td>
                <label th:for="challengeRating">CR: </label>
                <select id="challengeRating" th:field="*{challengeRating}">
                    <option th:text="'0'" th:value="0.0"></option>
                    <option th:text="'1/8'" th:value="0.125"></option>
                    <option th:text="'1/4'" th:value="0.25"></option>
                    <option th:text="'1/2'" th:value="0.5"></option>
                    <option th:each="i : ${#numbers.sequence(1,30)}" th:value="${i}"
                            th:text="${i}"></option>
                </select>
            </td>
            <td>

            </td>
        </tr>
        <tr id="abilityBoxes">
            <table th:each="ability, iter :${creature.abilities}" th:id="|ability${iter.index}|"
                   style="display: none;">
                <tr>
                    <td>
                        <label th:for="abilityName">Title: </label><input th:id="abilityName"
                                                                          th:field="*{abilities[__${iter.index}__].abilityName}"
                    th:placeholder="Ability Name">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" th:field="*{abilities[__${iter.index}__].abilityType}"
                               th:each="t : ${T(org.nerdcore.dmdatatools.GameEntities.CreatureAbility.AbilityType).values()}"
                               th:value="${t}" th:iter="${iter.index}"
                               th:text="${#strings.capitalizeWords(#strings.toLowerCase(#strings.replace(t, '_', ' ')))}"
                               th:onclick="|selectActionType(${iter.index})|">
                    </td>
                </tr>
                <tr style="display: none;" th:id="|damageRow${iter.index}|">
                    <td>

                        <label th:for="damage">HitDie:</label>
                        <input type="number" id="hitDieCount" name="hitDieCount" min="1" max="40"
                               value="1" th:onchange="writeHitDieDisplay()">
                        <select id="hitDieBase" name="hitDieBase" th:onchange="writeHitDieDisplay()">
                            <option th:value="4" th:text="'d4'" selected></option>
                            <option th:value="6" th:text="'d6'"></option>
                            <option th:value="8" th:text="'d8'"></option>
                            <option th:value="10" th:text="'d10'"></option>
                            <option th:value="12" th:text="'d12'"></option>
                            <option th:value="20" th:text="'d20'"></option>
                        </select></td>
                </tr>


                <tr>
                    <td>
                        <div th:id="|editor${iter.index}|"></div>
                        <input th:type="hidden" th:field="*{abilities[__${iter.index}__].abilityDescription}"
                               th:id="|abilityDescription${iter.index}|">
                    </td>
                </tr>

            </table>

        </tr>


    </table>
    <button th:onclick="" style="float: left;">Add Another Ability</button>
    <input type="submit" value="Submit" style="float:right">
</form>


</body>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script type="text/javascript">

    var quills = [];
    var abilityCount = 0;

    window.onload = function (ev) {
        document.getElementById("ability0").style.display = "table";

        for (var i = 0; i < 99; i++) {
            var quillID = '#editor' + i;
            quills.push(new Quill(quillID, {theme: 'snow'}));
        }
    };

    function addAbilitySubform(){
        abilityCount++;
        let abilityName = "ability"+abilityCount;
        document.getElementById(abilityName).style.display = "table";
    }

    function cleanupSubmitForm() {

        for (var i = 0; i < 99; i++) {
            document.getElementById('abilityDescription' + i).value = quills[i].root.innerHTML.toString();
        }

    }

    function writeHitDieDisplay() {
        var dieBase = parseInt(document.getElementById("hitDieBase").value);
        var dieCount = parseInt(document.getElementById("hitDieCount").value);
        var con = parseInt(document.getElementById("abilityScores2").value);
        var conHP = parseInt(((con - 10) / 2))* dieCount;
        var avgHP = parseInt(conHP + (((dieBase + 1) / 2.0)) * dieCount);
        document.getElementById("hpDisplay").innerText = `${avgHP} (${dieCount}d${dieBase} + ${conHP})`;
    }

    function selectActionType(actionNumber) {
        let radioName = 'abilities[' + actionNumber + '].abilityType';
        let radioList = document.getElementsByName(radioName);
        let selected = "";
        for (i = 0; i < radioList.length; i++) {
            if (radioList[i].checked) {
                selected = radioList[i].value;
            }
        }

        let damageRowName = "damageRow" + actionNumber;
        document.getElementById(damageRowName).style.display = "table";

        alert(selected);
    }


</script>
<script>

</script>

</html>