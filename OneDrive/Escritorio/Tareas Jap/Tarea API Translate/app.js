document.addEventListener('DOMContentLoaded', init);

function init(){
    getLanguages();
    trasnlateText();
}

async function getLanguages() {
    let errorMessage = {

    }
    const url = 'https://google-translate1.p.rapidapi.com/language/translate/v2/languages';
    const options = {
        method: 'GET',
        headers: {
            'Accept-Encoding': 'application/gzip',
            'X-RapidAPI-Key': 'b827c01b75mshb0095e169e8c2e9p13bdd5jsn010b9d958080',
            'X-RapidAPI-Host': 'google-translate1.p.rapidapi.com'
        }
    };

    try {
        const response = await fetch(url, options);
        const result = await response.json();
        if(!response.ok) {
            errorMessage.message = result.message;
        }
        
        const {data} = result;
        const {languages} = data;
        createLanguagesOptions(languages); 
        
    } catch (error) {
        document.querySelector(".container").classList.add("overlay")
        document.querySelector("#message-error").classList.remove("none");
        document.querySelector("#message-error").classList.add("display-message");
        document.querySelector("#error").textContent = errorMessage.message;
        console.log(errorMessage.message);
    }
}

function createLanguagesOptions(languages) {
    const idiomasSource = document.getElementById("idioma-source");
    const idiomasTarget = document.getElementById("idioma-target");

    languages.forEach(element => {
        const node = document.createElement("option");
        node.value = element.language;
        const textNode = document.createTextNode(element.language);
        node.appendChild(textNode);
        idiomasSource.appendChild(node);
    });

    languages.forEach(element => {
        const node = document.createElement("option");
        node.value = element.language;
        const textNode = document.createTextNode(element.language);
        node.appendChild(textNode);
        idiomasTarget.appendChild(node);
    });

    idiomasSource.value = "es";
    idiomasTarget.value = "en";
}

function trasnlateText() {
    const textareaSource = document.getElementById("message");
    const idiomasSource = document.getElementById("idioma-source");
    const idiomasTarget = document.getElementById("idioma-target");
    const btnTranslate = document.getElementById("traducir");
    const textareaTarget = document.getElementById("translate");
    
    
    btnTranslate.addEventListener("click", async () => {
        const url = 'https://google-translate1.p.rapidapi.com/language/translate/v2';
        const options = {
            method: 'POST',
            headers: {
                'content-type': 'application/x-www-form-urlencoded',
                'Accept-Encoding': 'application/gzip',
                'X-RapidAPI-Key': 'b827c01b75mshb0095e169e8c2e9p13bdd5jsn010b9d958080',
                'X-RapidAPI-Host': 'google-translate1.p.rapidapi.com'
            },
            body: new URLSearchParams({
                q: textareaSource.value,
                target: idiomasTarget.value,
                source: idiomasSource.value
            })
        };

        try {
            const response = await fetch(url, options);
            const result = await response.json();
            const data = result.data.translations[0];
            const { translatedText } = data;
            textareaTarget.placeholder = translatedText;
        } catch (error) {
            textareaTarget.placeholder = textareaSource.value;
        }

    })
}

