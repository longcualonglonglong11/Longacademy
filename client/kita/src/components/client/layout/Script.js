import ScriptTag from "react-script-tag";

export default function () {
    return(
        <div className="script-component">
        <ScriptTag
          isHydrating={false}
          src={"../../../assets/client/lib/jquery/jquery.slim.min.js"}
        />
        <ScriptTag
          isHydrating={false}
          src={"../../../assets/client/lib/popper.js/popper.min.js"}
        />
    
        <ScriptTag
          isHydrating={false}
          src={"../../../assets/client/lib/bootstrap/bootstrap.min.js"}
        />
    
        <ScriptTag
          isHydrating={false}
          src={"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"}
        />
    
        <ScriptTag
          isHydrating={false}
          src={"https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"}
        />
    
        <ScriptTag isHydrating={false} src={"https://unpkg.com/vue/dist/vue.js"} />
    
        <ScriptTag
          isHydrating={false}
          src={"https://unpkg.com/vue-router/dist/vue-router.js"}
        />
    
        <ScriptTag isHydrating={false} src={"../../../assets/client/js/main.js"} />
      </div>

    )
 
}
