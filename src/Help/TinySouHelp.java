package Help;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TinySouHelp {
	//本次搜索相关信息，包括搜索内容，结果数等等
    public final String INFO = "info";
    //搜索结果(document)的列表。
    public final String RECORDS = "records";
    //相关错误信息。
    public final String ERRORS = "errors";
    //---------------------------------------info部分---------------------------------------
    //本次搜索的搜索内容。例如："自定义样式 css"。
    public final String QUERY = "query";
    //本次搜索的 page 参数值。例如：1。
    public final String PAGE = "page";
    //本次搜索的 per_page 参数值。例如：10。
    public final String PER_PAGE = "per_page";
    //搜索结果的总数。例如：19。
    public final String TOTAL = "total";
    //搜索结果中，score的最大值。例如：1.3830765。
    public final String MAX_SCORE = "max_score";
    //---------------------------------------records部分---------------------------------------
    //该document所属collection。
    public final String COLLECTION = "collection";
    //该document的"score"。
    public final String SCORE = "score";
    //以标记出该document与搜索内容符合的部分。
    public final String HIGHTLIGHT = "highlight";
    //该document所包含的field。
    public final String DOCUMENT = "document";
    //***********************************document部分
    public final String ID = "id";
    public final String UPDATED_AT = "updated_at";
    public final String TITLE = "title";
    public final String URL = "url";
    public final String SECTIONS= "sections";
    public final String BODY  = "body";
    public final String TYPE = "type";
    public final String IMAGE = "image";
    public final String PUBLISHED_AT = "published_at";
    public final String POPULARITY = "popularity";
    //public final String INFO = "info";
    //---------------------------------------errors部分---------------------------------------
    //指定了不存在的 field
    public final String SEARCH_FIELD = "search_fields";

    //微搜索显示json格式的搜索结果
    protected JSONObject JsonResult = null;
    protected JSONObject ResultInfo = null;
    protected JSONArray ResultIRecords = null;
    protected JSONObject ResultErrors = null;
    //Info
    protected String ResultInfoQuery = null;
    protected String ResultInfoPage = null;
    protected String ResultInfoPer_Page = null;
    protected String ResultInfoTotal = null;
    protected String ResultInfoMax_score = null;
    //Records
    protected List ResultIRecordsCollectionList = new ArrayList();
    protected List ResultIRecordsScoreList = new ArrayList();
    protected List ResultIRecordsHighlightList = new ArrayList();
    //Records HighLight
    protected List ResultIRecordsHighlightBodyList = new ArrayList();
    protected List ResultIRecordsHighlightSectionsList = new ArrayList();
    //Records Document
    protected List ResultIRecordsDocumentList = new ArrayList();
    protected List ResultIRecordsDocumentIdList = new ArrayList();
    protected List ResultIRecordsDocumentUpdate_atList = new ArrayList();
    protected List ResultIRecordsDocumentTitleList = new ArrayList();
    protected List ResultIRecordsDocumentUrlList = new ArrayList();
    protected List ResultIRecordsDocumentSectionsList = new ArrayList();
    protected List ResultIRecordsDocumentBodyList = new ArrayList();
    protected List ResultIRecordsDocumentTypeList = new ArrayList();
    protected List ResultIRecordsDocumentImageList = new ArrayList();
    protected List ResultIRecordsDocumentPublished_atList = new ArrayList();
    protected List ResultIRecordsDocumentPopularityList = new ArrayList();
    protected List ResultIRecordsDocumentInfoList = new ArrayList();
    //ERRORs
    //...

    public TinySouHelp(){}

    public TinySouHelp(JSONObject JsonResult){ this.JsonResult = JsonResult;}
    //-----------------------------------------info处理---------------------------------------------
    //query
    public String getInfoQuery() throws Exception {
        this.ResultInfoQuery = this.JsonResult.getJSONObject(INFO).getString(QUERY);
        return this.ResultInfoQuery;
    }

    //page
    public String getInfoPage() throws Exception {
        this.ResultInfoPage = this.JsonResult.getJSONObject(INFO).getString(PAGE);
        return this.ResultInfoPage ;
    }

    //query
    public String getInfoPer_page() throws Exception {
        this.ResultInfoPer_Page = this.JsonResult.getJSONObject(INFO).getString(PER_PAGE);
        return this.ResultInfoPer_Page;
    }

    //total
    public String getInfoTotal() throws Exception {
        this.ResultInfoTotal = this.JsonResult.getJSONObject(INFO).getString(TOTAL);
        return this.ResultInfoTotal;
    }

    //max_score
    public String getInfoMax_score() throws Exception {
        this.ResultInfoMax_score = this.JsonResult.getJSONObject(INFO).getString(MAX_SCORE);
        return this.ResultInfoMax_score;
    }

    //-----------------------------------------record 处理------------------------------------------
    //collection
    public List getRecordsCollection() throws Exception {
        this.ResultIRecords = this.JsonResult.getJSONArray(RECORDS);
        int length = Integer.parseInt(this.getInfoTotal());
        for(int i=0;i<this.ResultIRecords.length();i++){
            JSONObject ResultIRecordsJson = new JSONObject(this.ResultIRecords.getString(i));
            this.ResultIRecordsCollectionList.add(ResultIRecordsJson.getString(COLLECTION));
        }
        return this.ResultIRecordsCollectionList;
    }

    //score
    public List getRecordsScore() throws Exception {
        this.ResultIRecords = this.JsonResult.getJSONArray(RECORDS);
        int length = Integer.parseInt(this.getInfoTotal());
        for(int i=0;i<this.ResultIRecords.length();i++){
            JSONObject ResultIRecordsJson = new JSONObject(this.ResultIRecords.getString(i));
            this.ResultIRecordsScoreList.add(ResultIRecordsJson.getString(SCORE));
        }
        return this.ResultIRecordsScoreList;
    }

    //highlight
    public List getRecordsHighlight() throws Exception {
        this.ResultIRecords = this.JsonResult.getJSONArray(RECORDS);
        int length = Integer.parseInt(this.getInfoTotal());
        for(int i=0;i<this.ResultIRecords.length();i++){
            JSONObject ResultIRecordsJson = new JSONObject(this.ResultIRecords.getString(i));
            this.ResultIRecordsHighlightBodyList.add(ResultIRecordsJson.getJSONObject(HIGHTLIGHT).getString(BODY));
            this.ResultIRecordsHighlightSectionsList.add(ResultIRecordsJson.getJSONObject(HIGHTLIGHT).getString(SECTIONS));
        }
        this.ResultIRecordsHighlightList.add(this.ResultIRecordsHighlightBodyList);
        this.ResultIRecordsHighlightList.add(this.ResultIRecordsDocumentSectionsList);
        return this.ResultIRecordsHighlightList;
    }


    //document
    public List getRecordsDocument() throws Exception {
        String[] NameList = {ID,UPDATED_AT,TITLE,URL,SECTIONS,BODY,TYPE,IMAGE,
                PUBLISHED_AT,POPULARITY,INFO};
        List[] DocumentList = {this.ResultIRecordsDocumentIdList,this.ResultIRecordsDocumentUpdate_atList,
                this.ResultIRecordsDocumentTitleList, this.ResultIRecordsDocumentUrlList,
                this.ResultIRecordsDocumentSectionsList,this.ResultIRecordsDocumentBodyList,
                this.ResultIRecordsDocumentTypeList,this.ResultIRecordsDocumentImageList,
                this.ResultIRecordsDocumentPublished_atList,this.ResultIRecordsDocumentPopularityList,
                this.ResultIRecordsDocumentInfoList};
        this.ResultIRecords = this.JsonResult.getJSONArray(RECORDS);
        for(int i=0;i<this.ResultIRecords.length();i++){
            JSONObject ResultIRecordsJson = new JSONObject(this.ResultIRecords.getString(i));
            for(int j=0;j<11;j++) {
                DocumentList[j].add(ResultIRecordsJson.getJSONObject(DOCUMENT).getString(NameList[j]));
            }
        }
        for(int i=0;i<DocumentList.length;i++) {
            this.ResultIRecordsDocumentList.add(DocumentList[i]);
        }
        return ResultIRecordsDocumentList;
    }
}
