const HOSTADDR = "192.168.219.109";
const PORT = "8080";

async function fetchUsers (gender) {
    const res = await fetch("https://dummyjson.com/users/filter?key=gender&value="+gender+"&limit=30&select=firstName,gender");
    const data = await res.json();
    return data.users;
};

async function fetchColleges () {
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/major/college");
    const data = await res.json();
    return data;
};

async function fetchMajors(college){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/major/college/"+college);
    const data = await res.json();
    return data;
};

async function fetchProf(major_ID){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/prof/major/"+major_ID);
    const data = await res.json();
    return data;
}

async function fetchBoards(){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards");
    const data = await res.json();
    return data;
}

async function writeBoard({content, profId}){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    content: content,
                    professor: {
                        "profId": profId,
                    },
                })
            });
    const data = await res.json();
    return data;
}

async function deleteBoard({boardId}){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards/"+boardId, {
                method: "DELETE",
            });
    const data = await res.json();
    return data;
}
export {fetchUsers, fetchColleges, fetchMajors, fetchProf, fetchBoards, writeBoard, deleteBoard};