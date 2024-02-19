import { restoreToken } from "./AuthFunc";
import { HOSTADDR, PORT } from './Configuration';

async function fetchUsers (gender) {
    const res = await fetch("https://dummyjson.com/users/filter?key=gender&value="+gender+"&limit=30&select=firstName,gender");
    const data = await res.json();
    return data.users;
};

async function fetchColleges () {
    const AUTH_TOKEN = await restoreToken();
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/all-colleges", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+AUTH_TOKEN
        },
    });
    const data = await res.json();
    return data.response;
};

async function fetchMajors(collegeId){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/all-colleges/categories/"+(collegeId+66));
    const data = await res.json();
    return data.response;
};

async function fetchProf(major_ID){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/all-professors/major/"+major_ID);
    const data = await res.json();
    return data.response;
}

async function fetchBoards(){
    
    try{
        const AUTH_TOKEN = await restoreToken();
        const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards",{
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+AUTH_TOKEN
        },
        });
        const data = await res.json();
        return data.response;
    } catch(e){
        console.log("fetchBoardsError: ", e);
    }    
    
    // return data;
}

async function writeBoard({content, profId}){
    try{
        const AUTH_TOKEN = await restoreToken();

        const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards/"+profId, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+AUTH_TOKEN
                    },
                    body: JSON.stringify({
                        content: content
                    })
                });
        const data = await res.json();
        return data.response;
    } catch(e){
        console.log("writeBoardError: ", e);
    }
}

async function deleteBoard({boardId}){
    const res = await fetch("http://"+HOSTADDR+":"+PORT+"/boards/"+boardId, {
                method: "DELETE",
            });
    const data = await res.json();
    return data;
}


async function fetchComments({boardId}){
    try{
        const AUTH_TOKEN = await restoreToken();

        const res = await fetch("http://"+HOSTADDR+":"+PORT+"/comments/"+boardId,{
                        method: "GET",
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer '+AUTH_TOKEN
                        },
                    });
        const data = await res.json();
        return data.response;
    } catch (e){
        console.log("fetchCommentsError: ", e);
    }
}

async function writeComments({boardId, content}){
    try{
        const AUTH_TOKEN = await restoreToken();

        const res = await fetch("http://"+HOSTADDR+":"+PORT+"/comments/"+boardId,{
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer '+AUTH_TOKEN
                        },
                        body: JSON.stringify({
                            content: content
                        })
                    });
        const data = await res.json();
        return data.response;
    } catch (e){
        console.log("writeCommentsError: ", e);
    }
}

export {fetchUsers, fetchColleges, fetchMajors, fetchProf, fetchBoards, writeBoard, deleteBoard, fetchComments, writeComments};